package org.firstinspires.ftc.team7316.util.commands.flow;

import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by andrew on 11/2/16.
 */
public class SimultaneousCommands extends Command implements TerminatedListener {

    private ArrayList<Command> cmds = new ArrayList<>();

    public SimultaneousCommands(Command... cmds) {
        Collections.addAll(this.cmds, cmds);

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
            Scheduler.instance.add(cmd);
            cmd.shouldReplace = false;
        }
    }

    @Override
    public void loop() {
    }

    @Override
    public boolean shouldRemove() {
        return this.cmds.size() <= 0;
    }

    @Override
    public void end() {
        for (Subsystem subsystem : this.requiredSubsystems) {
            subsystem.needsDefault = true;
        }
        for (Command cmd : this.cmds) {
            cmd.shouldReplace = true;
            cmd.terminatedListener = null;
        }
    }

    @Override
    public void onTerminated(Command terminated) {
        this.cmds.remove(terminated);
    }
}
