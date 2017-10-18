package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

/**
 * Created by andrew on 10/18/17.
 */

public class BlankCommand extends Command {

    private Subsystem required;

    public BlankCommand(Subsystem required) {
        this.required = required;
    }

    @Override
    public Subsystem requiredSubystem() {
        return this.required;
    }

    @Override
    public void onInit() {
    }

    @Override
    public boolean shouldEnd() {
        return false;
    }

    @Override
    public void loop() {

    }

    @Override
    public void terminate() {

    }
}
