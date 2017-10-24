package org.firstinspires.ftc.team7316.util.commands.flow;

import org.firstinspires.ftc.team7316.modes.CommandAuto;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

/**
 * Created by andrew on 10/21/17.
 */

public class SequentialCommand extends Command implements TerminatedListener {

    private Command[] cmds;
    private int index = 0;

    public SequentialCommand(Command... commands) {
        this.cmds = commands;

        for (Command cmd : commands) {
            cmd.terminatedListener = this;
            for (Subsystem sub : cmd.requiredSubsystems) {
                this.requires(sub);
            }
        }
    }

    @Override
    public void init() {
        for (Subsystem subsystem : this.requiredSubsystems) {
            subsystem.needsDefault = false;
        }
        index = 0;

        if (index < cmds.length) {
            Command cmd = cmds[index];
            cmd.shouldReplace = false;
            Scheduler.instance.add(cmd);
        }
    }

    @Override
    public void loop() {
    }

    @Override
    public boolean shouldRemove() {
        return index >= cmds.length;
    }

    @Override
    public void end() {
        for (Subsystem subsystem : this.requiredSubsystems) {
            subsystem.needsDefault = true;
        }
        for (Command cmd : this.cmds) {
            cmd.terminatedListener = null;
            cmd.shouldReplace = true;
        }
    }

    @Override
    public void onTerminated(Command terminated) {
        index++;
        if (index < cmds.length) {
            Command cmd = cmds[index];
            cmd.shouldReplace = false;
            Scheduler.instance.add(cmd);
        }
    }
}
