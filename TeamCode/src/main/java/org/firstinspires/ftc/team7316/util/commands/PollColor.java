package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 11/14/17.
 */

public class PollColor extends Command {
    @Override
    public void init() {
        requires(Subsystems.instance.jewelArm);
        Hardware.instance.colorWrapper.run();
        Hardware.instance.colorWrapper.setNoColor();
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return true;
    }

    @Override
    protected void end() {

    }
}
