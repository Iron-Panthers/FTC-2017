package org.firstinspires.ftc.team7316.modes;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;

/**
 * Used for those autonomodes that have a single command (basically a lot of files)
 * It takes in a command in the constructor.
 */
public abstract class CommandAuto extends BaseOpMode {

    protected abstract Loopable getTask();

    @Override
    public void init() {
        super.init();
        Scheduler.instance.addTask(getTask());
    }

    @Override
    public void onInit() { // Make the classes more compact

    }

    @Override
    public void onLoop() {

    }

}
