package org.firstinspires.ftc.team7316.util.commands;


import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

/**
 * A Loopable with a required subsystem
 */

public abstract class Command implements Loopable {

    private boolean overrideEnd = false;
    public void end() {
        overrideEnd = true;
    }

    public abstract Subsystem requiredSubystem();

    public void start() {
        this.requiredSubystem().setCurrentCmd(this);
    }

    public abstract void onInit();
    @Override
    public void init() {
        overrideEnd = false;
        onInit();
    }

    public abstract boolean shouldEnd();
    @Override
    public boolean shouldRemove() {
        if (overrideEnd) {
            return true;
        }

        return shouldEnd();
    }

}
