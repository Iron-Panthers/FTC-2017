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
    private final double closeOffset = 1;
    private final double farOffset = -2;

    private Alliance alliance;
    private Position position;

    private final double LEFT_DIST;
    private final double CENTER_DIST;
    private final double RIGHT_DIST;

    public DriveDistanceCipher(Alliance a, Position p) {
        alliance = a;
        position = p;

        if(position == Position.CLOSE) {
            LEFT_DIST = Constants.LEFT_COLUMN_DISTANCE_CLOSE + closeOffset;
            CENTER_DIST = Constants.MIDDLE_COLUMN_DISTANCE_CLOSE + closeOffset;
            RIGHT_DIST = Constants.RIGHT_COLUMN_DISTANCE_CLOSE + closeOffset;
        }
        else {
            LEFT_DIST = Constants.LEFT_COLUMN_DISTANCE_FAR + farOffset;
            CENTER_DIST = Constants.MIDDLE_COLUMN_DISTANCE_FAR + farOffset;
            RIGHT_DIST = Constants.RIGHT_COLUMN_DISTANCE_FAR + farOffset;
        }
    }

    @Override
    public void init() {
        //holy fucking shit
        switch (alliance) {
            case RED:
                switch (Hardware.instance.vuforiaCameraWrapper.vuMark) {
                    case LEFT:
                        drivecommand = new DriveDistance(LEFT_DIST, DRIVE_TIMEOUT);
                        break;
                    case CENTER:
                        drivecommand = new DriveDistance(CENTER_DIST, DRIVE_TIMEOUT);
                        break;
                    case RIGHT:
                        drivecommand = new DriveDistance(RIGHT_DIST, DRIVE_TIMEOUT);
                        break;
                    default:
                        drivecommand = new DriveDistance(CENTER_DIST, DRIVE_TIMEOUT);
                        break;
                }
                break;
            case BLUE:
                switch (Hardware.instance.vuforiaCameraWrapper.vuMark) {
                    //yes it's confusing no im not going to fix it
                    case LEFT:
                        drivecommand = new DriveDistance(-RIGHT_DIST, DRIVE_TIMEOUT);
                        break;
                    case CENTER:
                        drivecommand = new DriveDistance(-CENTER_DIST, DRIVE_TIMEOUT);
                        break;
                    case RIGHT:
                        drivecommand = new DriveDistance(-LEFT_DIST , DRIVE_TIMEOUT);
                        break;
                    default:
                        drivecommand = new DriveDistance(-CENTER_DIST, DRIVE_TIMEOUT);
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

    public enum Position {
        FAR, CLOSE
    }
}
