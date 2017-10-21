package org.firstinspires.ftc.team7316.util.commands.flow;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by andrew on 11/2/16.
 */
public class SimultaneousCommands extends Command {

    private Command[] cmds;

    public SimultaneousCommands(Command... cmds) {
        this.cmds = cmds;

        for (Command cmd : this.cmds) {
            for (Subsystem subsystem : cmd.requiredSubsystems) {
                this.requires(subsystem);
            }
        }
    }

    @Override
    public void init() {
        for (Subsystem subsystem : this.requiredSubsystems) {
            subsystem.needsDefault = false;
        }

        for (Command cmd : this.cmds) {
            Scheduler.instance.add(cmd);
        }
    }

    @Override
    public void loop() {
    }

    @Override
    public boolean shouldRemove() {
        for (Command cmd : cmds) {
            if (!cmd.shouldRemove()) {
                return false;
            }
        }

        for (Subsystem subsystem : this.requiredSubsystems) {
            subsystem.needsDefault = true;
        }

        return true;
    }

    @Override
    public void end() {
    }

}
