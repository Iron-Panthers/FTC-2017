package org.firstinspires.ftc.team7316.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

/**
 * Created by andrew on 9/15/16.
 */
public class Scheduler {

    public static boolean inTeleop = true;
    public static final Scheduler instance = new Scheduler();

    private ArrayList<Command> commands = new ArrayList<>();
    private ArrayList<Command> newCommandBuffer = new ArrayList<>();

    private Scheduler () {}

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public ArrayList<Command> getBuffer() {
        return newCommandBuffer;
    }

    private int offset = 0; // need to counter act issues if a command is added during addFromBuffer
    public void add(Command newCommand) {
        if(newCommand != null) {
            offset++; // if a command is added increase offset (this will be reset at the beginning of addFromBuffer
            newCommandBuffer.add(0, newCommand);
        }
    }

    public void loop() {

        addFromBuffer();

        for (int i = commands.size() - 1; i >= 0; i--) {
            Command cmd = commands.get(i);
            cmd.loop();

            boolean shouldRemove = cmd.shouldRemove();
            boolean isNotNull = cmd.terminatedListener != null;
            boolean listener = (isNotNull && cmd.terminatedListener.isDone());
            Log.d(Hardware.tag, String.format("Scheduler remove conditions for command %s: %s %s %s", cmd.toString(), shouldRemove, isNotNull, listener));

            if (shouldRemove || listener) {
                commands.remove(i);

                //System.out.println(cmd.getClass() + " removed.");
                Log.i(Hardware.tag, "Scheduler removing command " + cmd.toString());

                cmd._end();
                cmd.terminatedListener = null;  

                for (Subsystem subsystem : cmd.requiredSubsystems) {
                    if (subsystem.needsDefault && subsystem.hasDefault()) {
                        // we set the command to null so that it isn't interrupted.
                        // If we didn't do this, the command we receive "end" and also "interrupt"
                        subsystem.currentCmd = null;

                        Log.i(Hardware.tag, "Scheduler adding default command to subsystem " + subsystem.toString());
                        this.add(subsystem.getDefaultCommand());
                    }
                }
            }
        }
    }

    private void addFromBuffer() {
        offset = 0; // reset offset, so that the only offset is due to commands added during addFromBuffer
        int size = newCommandBuffer.size();
        for (int i = size - 1; i >= offset; i--) { // make sure we end at the most recent command
                                                   // that was added BEFORE this method began
            Command newCmd = newCommandBuffer.get(i);
            newCommandBuffer.remove(i);
            commands.add(newCmd);

            for (Subsystem subsystem : newCmd.requiredSubsystems) {
                if (subsystem.currentCmd != null && subsystem.currentCmd.shouldBeReplaced) {
                    subsystem.currentCmd.interrupt();
                    this.commands.remove(subsystem.currentCmd);

                    subsystem.currentCmd = newCmd;
                }

                if (subsystem.currentCmd == null) {
                    subsystem.currentCmd = newCmd;
                }
            }

            newCmd.init();
        }
    }

    public void clear() {
        this.commands.clear();
    }

}
