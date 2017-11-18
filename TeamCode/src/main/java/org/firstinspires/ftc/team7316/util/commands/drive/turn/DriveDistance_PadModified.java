package org.firstinspires.ftc.team7316.util.commands.drive.turn;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveDistance;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveForTime;

/**
 * Created by jerry on 11/18/17.
 */

public class DriveDistance_PadModified extends Command {

    private int distance;
    private DriveDistance cmd;

    public DriveDistance_PadModified(double distance) {
        this.distance = (int)Constants.distanceToTicks(distance);
    }

    @Override
    public void init() {
        if(Hardware.instance.colorWrapper.drivenForward) {
            cmd = new DriveDistance(distance - Constants.DISTANCE_PAD_OFFSET);
        }
        else {
            cmd = new DriveDistance(distance + Constants.DISTANCE_PAD_OFFSET);
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
