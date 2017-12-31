package org.firstinspires.ftc.team7316.util.commands.drive.distance;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * This class is purely for the close side autos, along with those constants
 */

public class DriveDistanceCipher extends Command {

    private DriveDistance drivecommand;
    private final double DRIVE_TIMEOUT = 4;

    private Alliance alliance;

    public DriveDistanceCipher(Alliance a) {
        alliance = a;
    }

    @Override
    public void init() {
        //holy fucking shit
        switch (alliance) {
            case RED:
                switch (Hardware.instance.vuforiaCameraWrapper.vuMark) {
                    case LEFT:
                        drivecommand = new DriveDistance(Constants.LEFT_COLUMN_DISTANCE, DRIVE_TIMEOUT);
                        break;
                    case CENTER:
                        drivecommand = new DriveDistance(Constants.MIDDLE_COLUMN_DISTANCE, DRIVE_TIMEOUT);
                        break;
                    case RIGHT:
                        drivecommand = new DriveDistance(Constants.RIGHT_COLUMN_DISTANCE, DRIVE_TIMEOUT);
                        break;
                    default:
                        drivecommand = new DriveDistance(Constants.MIDDLE_COLUMN_DISTANCE, DRIVE_TIMEOUT);
                        break;
                }
                break;
            case BLUE:
                switch (Hardware.instance.vuforiaCameraWrapper.vuMark) {
                    //yes it's confusing no im not going to fix it
                    case LEFT:
                        drivecommand = new DriveDistance(Constants.RIGHT_COLUMN_DISTANCE * -1, DRIVE_TIMEOUT);
                        break;
                    case CENTER:
                        drivecommand = new DriveDistance(Constants.MIDDLE_COLUMN_DISTANCE * -1, DRIVE_TIMEOUT);
                        break;
                    case RIGHT:
                        drivecommand = new DriveDistance(Constants.LEFT_COLUMN_DISTANCE * -1, DRIVE_TIMEOUT);
                        break;
                    default:
                        drivecommand = new DriveDistance(Constants.MIDDLE_COLUMN_DISTANCE * -1, DRIVE_TIMEOUT);
                        break;
                }
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
