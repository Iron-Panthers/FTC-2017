package org.firstinspires.ftc.team7316.util.commands.sensors;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 11/14/17.
 */

public class PollColor extends Command {

    private int iteration = 0;

    public PollColor() {
        requires(Subsystems.instance.jewelArm);
    }

    @Override
    public void init() {
        Hardware.instance.colorWrapper.reset();
    }

    @Override
    public void loop() {
        Hardware.instance.colorWrapper.push();
        Hardware.instance.colorWrapper.logSums();
        Hardware.instance.colorWrapper.logColors();
        iteration++;
    }

    @Override
    public boolean shouldRemove() {
        return iteration >= Constants.COLOR_BUFFER_SIZE;
    }

    @Override
    protected void end() {
        Hardware.instance.colorWrapper.setNoColor();
    }
}
