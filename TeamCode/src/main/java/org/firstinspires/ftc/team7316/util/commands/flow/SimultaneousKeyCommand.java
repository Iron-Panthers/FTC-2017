package org.firstinspires.ftc.team7316.util.commands.flow;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.commands.TerminatedListener;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by andrew on 2/1/18.
 */

public class SimultaneousKeyCommand extends Command implements TerminatedListener {

    private ArrayList<Command> cmds = new ArrayList<>();
    private Command keyCommand;
    private boolean done = false;

    public SimultaneousKeyCommand(Command keyCommand, Command... cmds) {
        Collections.addAll(this.cmds, cmds);
        this.cmds.add(keyCommand);
        this.keyCommand = keyCommand;

        this.shouldBeReplaced = false;
        for (Command cmd : this.cmds) {
            cmd.terminatedListener = this;
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
            cmd.shouldBeReplaced = false;
            Scheduler.instance.add(cmd);
        }
    }

    @Override
    public void loop() {
    }

    @Override
    public boolean shouldRemove() {
        return this.keyCommand.shouldRemove();
    }

    @Override
    public void end() {
        this.done = true;

        for (Subsystem subsystem : this.requiredSubsystems) {
            subsystem.needsDefault = true;
        }
        for (Command cmd : this.cmds) {
            cmd.shouldBeReplaced = true;
            cmd.terminatedListener = null;
        }
    }

    @Override
    public void onTerminated(Command terminated) {
    }

    @Override
    public boolean isDone() {
        return done;
    }
}
