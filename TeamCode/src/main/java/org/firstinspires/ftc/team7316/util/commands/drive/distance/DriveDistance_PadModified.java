package org.firstinspires.ftc.team7316.util.commands.drive.distance;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by jerry on 11/18/17.
 */

public class DriveDistance_PadModified extends Command {

    private double distance;
    private DriveDistance cmd;

    public DriveDistance_PadModified(double distance) {
        this.distance = distance;
    }

    @Override
    public void init() {
        if(Hardware.instance.colorWrapper.drivenForward) {
            cmd = new DriveDistance(distance - Constants.DISTANCE_ERROR_RANGE, 10);
        }
        else {
            cmd = new DriveDistance(distance + Constants.DISTANCE_ERROR_RANGE, 10);
        }
        cmd.init();
    }

    @Override
    public void loop() {
        cmd.loop();

    }

    @Override
    public boolean shouldRemove() {
        return cmd.shouldRemove();
    }

    @Override
    protected void end() {
        cmd.interrupt();
    }
}
