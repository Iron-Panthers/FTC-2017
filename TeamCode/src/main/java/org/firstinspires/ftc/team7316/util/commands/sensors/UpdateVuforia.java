package org.firstinspires.ftc.team7316.util.commands.sensors;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by jerry on 12/30/17.
 */

public class UpdateVuforia extends Command {
    @Override
    public void init() {

    }

    @Override
    public void loop() {
        Hardware.instance.vuforiaCameraWrapper.update();
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {

    }
}