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

    public void add(Command newCommand) {
        if(newCommand != null) {
            newCommandBuffer.add(newCommand);
        }
    }

    public void loop() {
        String cmdsString = "";
        for (Command cmd : commands) {
            cmdsString += cmd.getClass();
        }
        Hardware.log("Commands", cmdsString);

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
        while (newCommandBuffer.size() > 0) {
            Command newCmd = newCommandBuffer.remove(0);
            commands.add(newCmd);
            Log.i(Hardware.tag, "Scheduler adding command " + newCmd.toString());

            for (Subsystem subsystem : newCmd.requiredSubsystems) {
                if (subsystem.currentCmd != null && subsystem.currentCmd.shouldBeReplaced) {
                    subsystem.currentCmd.interrupt();
                    this.commands.remove(subsystem.currentCmd);
                    Log.i(Hardware.tag, "Scheduler removing command " + subsystem.currentCmd.toString() + " from subsystem " + subsystem.toString());

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
