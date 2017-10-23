package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

/**
 * Created by andrew on 10/18/17.
 */

public class BlankCommand extends Command {

    public BlankCommand(Subsystem required) {
        requires(required);
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void end() {

    }
}
