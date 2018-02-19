package org.firstinspires.ftc.team7316.util.commands.drive.distance;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * The robot is parallel with the cryptobox in this scenario, starting from just off the
 * balancing stone.
 *
 * Currently has conditions for far auto but they will be removed later
 */

public class DriveDistanceCipherClose extends Command {

    private DriveDistance drivecommand;
    private final double DRIVE_TIMEOUT = 4;

    private final double blueCloseOffset = 1;
    private final double redCloseOffset = -1;
//    private final double closeOffset = 2;

    private final double redFarOffset = -2;
    private final double blueFarOffset = -3;
//    private final double farOffset = -3;

    private Alliance alliance;
    private Position position;

    private double LEFT_DIST;
    private double CENTER_DIST;
    private double RIGHT_DIST;

    public DriveDistanceCipherClose(Alliance a, Position p) {
        requires(Subsystems.instance.driveBase);

        alliance = a;
        position = p;

        if(position == Position.CLOSE) {
            switch (alliance) {
                case RED:
                    LEFT_DIST = Constants.LEFT_COLUMN_DISTANCE_CLOSE + redCloseOffset;
                    CENTER_DIST = Constants.MIDDLE_COLUMN_DISTANCE_CLOSE + redCloseOffset;
                    RIGHT_DIST = Constants.RIGHT_COLUMN_DISTANCE_CLOSE + redCloseOffset;
                case BLUE:
                    LEFT_DIST = Constants.LEFT_COLUMN_DISTANCE_CLOSE + blueCloseOffset - 2;
                    CENTER_DIST = Constants.MIDDLE_COLUMN_DISTANCE_CLOSE + blueCloseOffset;
                    RIGHT_DIST = Constants.RIGHT_COLUMN_DISTANCE_CLOSE + blueCloseOffset - 1;

            }
        }
        else {
            switch (alliance) {
                case RED:
                    LEFT_DIST = Constants.FAR_COLUMN_DISTANCE_FAR + redFarOffset;
                    CENTER_DIST = Constants.MIDDLE_COLUMN_DISTANCE_FAR + redFarOffset;
                    RIGHT_DIST = Constants.CLOSE_COLUMN_DISTANCE_FAR + redFarOffset;
                case BLUE:
                    LEFT_DIST = Constants.FAR_COLUMN_DISTANCE_FAR + blueFarOffset;
                    CENTER_DIST = Constants.MIDDLE_COLUMN_DISTANCE_FAR + blueFarOffset;
                    RIGHT_DIST = Constants.CLOSE_COLUMN_DISTANCE_FAR + blueFarOffset;

            }
        }
    }

    @Override
    public void init() {
        //holy fucking shit
        switch (alliance) {
            case RED:
                switch (Hardware.instance.vuforiaCameraWrapper.vuMark) {
                    case LEFT:
                        drivecommand = new DriveDistance(Constants.inchesToTicks(LEFT_DIST), DRIVE_TIMEOUT);
                        break;
                    case CENTER:
                        drivecommand = new DriveDistance(Constants.inchesToTicks(CENTER_DIST), DRIVE_TIMEOUT);
                        break;
                    case RIGHT:
                        drivecommand = new DriveDistance(Constants.inchesToTicks(RIGHT_DIST), DRIVE_TIMEOUT);
                        break;
                    default:
                        drivecommand = new DriveDistance(Constants.inchesToTicks(CENTER_DIST), DRIVE_TIMEOUT);
                        break;
                }
                break;
            case BLUE:
                switch (Hardware.instance.vuforiaCameraWrapper.vuMark) {
                    //yes it's confusing no im not going to fix it
                    case LEFT:
                        drivecommand = new DriveDistance(Constants.inchesToTicks(-RIGHT_DIST), DRIVE_TIMEOUT);
                        break;
                    case CENTER:
                        drivecommand = new DriveDistance(Constants.inchesToTicks(-CENTER_DIST), DRIVE_TIMEOUT);
                        break;
                    case RIGHT:
                        drivecommand = new DriveDistance(Constants.inchesToTicks(-LEFT_DIST) , DRIVE_TIMEOUT);
                        break;
                    default:
                        drivecommand = new DriveDistance(Constants.inchesToTicks(-CENTER_DIST), DRIVE_TIMEOUT);
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
