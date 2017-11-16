package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

/**
 * Created by andrew on 10/18/17.
 */

public class BlankCommand extends Command {

    private boolean shouldRemove;

    public BlankCommand(Subsystem required) {
        requires(required);
        shouldRemove = false;
    }

    public BlankCommand(Subsystem required, boolean shouldRemove) {
        requires(required);
        this.shouldRemove = shouldRemove;

    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return shouldRemove;
    }

    @Override
    public void end() {

    }
}
