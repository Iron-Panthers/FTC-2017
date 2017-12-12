package org.firstinspires.ftc.team7316.util.commands.drive.distance;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by jerry on 12/11/17.
 */

public class DriveDistanceCipher extends Command {

    private DriveDistance drivecommand;

    @Override
    public void init() {
        switch (Hardware.instance.vuforiaCameraWrapper.getVuMark()) {
            case LEFT:
                drivecommand = new DriveDistance(Constants.RIGHT_COLUMN_DISTANCE);
                break;
            case CENTER:
                drivecommand = new DriveDistance(Constants.MIDDLE_COLUMN_DISTANCE);
                break;
            case RIGHT:
                drivecommand = new DriveDistance(Constants.RIGHT_COLUMN_DISTANCE);
                break;
            default:
                drivecommand = new DriveDistance(Constants.MIDDLE_COLUMN_DISTANCE);
                break;
        }
        drivecommand.init();
    }

    @Override
    public void loop() {
        drivecommand.loop();
    }

    @Override
    public boolean shouldRemove() {
        return drivecommand.shouldRemove();
    }

    @Override
    protected void end() {
        drivecommand.end();
    }
}