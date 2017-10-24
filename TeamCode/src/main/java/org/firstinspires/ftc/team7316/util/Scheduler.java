package org.firstinspires.ftc.team7316.util;

import java.util.ArrayList;
import java.util.HashMap;
import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

/**
 * Created by andrew on 9/15/16.
 */
public class Scheduler {

    public static boolean inTeleop = false;
    public static final Scheduler instance = new Scheduler();

    private ArrayList<Command> commands = new ArrayList<>();
    private ArrayList<Command> newCommandBuffer = new ArrayList<>();

    private Scheduler () {}

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void add(Command newCommand) {
        newCommandBuffer.add(newCommand);
    }

    public void loop() {
        addFromBuffer();

        for (int i = commands.size() - 1; i >= 0; i--) {
            Command cmd = commands.get(i);
            cmd.loop();

            if (cmd.shouldRemove()) {
                commands.remove(i);
                cmd._end();

                for (Subsystem subsystem : cmd.requiredSubsystems) {
                    if (subsystem.needsDefault) {
                        // we set the command to null so that it isn't interrupted.
                        // If we didn't do this, the command we receive "end" and also "interrupt"
                        subsystem.currentCmd = null;

                        this.add(subsystem.getDefaultCommand());
                    }
                }
            }
        }
    }

    private void addFromBuffer() {
        int size = newCommandBuffer.size();
        for (int i = size - 1; i >= 0; i--) {
            Command newCmd = newCommandBuffer.get(i);
            newCommandBuffer.remove(i);
            commands.add(newCmd);

            if (newCmd.shouldReplace) {
                for (Subsystem subsystem : newCmd.requiredSubsystems) {
                    if (subsystem.currentCmd != null) {
                        subsystem.currentCmd.interrupt();
                        this.commands.remove(subsystem.currentCmd);
                    }

                    subsystem.currentCmd = newCmd;
                }
            }

            newCmd.init();
        }
    }

    public void clear() {
        this.commands.clear();
    }

    public static void main(String[] args) {
        //chris was here :::))))))) in me ass heHAA

        
    }

}
