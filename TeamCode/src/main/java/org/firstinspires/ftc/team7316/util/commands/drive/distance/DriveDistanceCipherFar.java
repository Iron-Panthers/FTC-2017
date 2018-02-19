package org.firstinspires.ftc.team7316.util.commands.drive.distance;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * The robot will drive straight towards the cryptobox after a turn towards the correct column.
 */

public class DriveDistanceCipherFar extends Command {

    private Alliance alliance;
    private double dist;
    private DriveDistance cmd;

    public DriveDistanceCipherFar(Alliance a) {
        alliance = a;
    }

    @Override
    public void init() {
        switch (alliance) {
            case RED:
                switch (Hardware.instance.vuforiaCameraWrapper.vuMark) {
                    case LEFT:
                        dist = Constants.PLATE_TO_CRYPTO_FAR / Math.cos(Constants.FAR_COLUMN_ANGLE_FAR * Constants.DEGREES_TO_RADIANS);
                        break;
                    case RIGHT:
                        dist = Constants.PLATE_TO_CRYPTO_FAR / Math.cos(Constants.CLOSE_COLUMN_ANGLE_FAR * Constants.DEGREES_TO_RADIANS);
                        break;
                    default:
                        dist = Constants.PLATE_TO_CRYPTO_FAR / Math.cos(Constants.MIDDLE_COLUMN_ANGLE_FAR * Constants.DEGREES_TO_RADIANS);
                        break;
                }
                break;
            case BLUE:
                switch (Hardware.instance.vuforiaCameraWrapper.vuMark) {
                    //yes it's confusing no im not going to fix it
                    case LEFT:
                        dist = Constants.PLATE_TO_CRYPTO_FAR / Math.cos(Constants.CLOSE_COLUMN_ANGLE_FAR * Constants.DEGREES_TO_RADIANS);
                        break;
                    case RIGHT:
                        dist = Constants.PLATE_TO_CRYPTO_FAR / Math.cos(Constants.FAR_COLUMN_ANGLE_FAR * Constants.DEGREES_TO_RADIANS);
                        break;
                    default:
                        dist = Constants.PLATE_TO_CRYPTO_FAR / Math.cos(Constants.MIDDLE_COLUMN_ANGLE_FAR * Constants.DEGREES_TO_RADIANS);
                        break;
                }
                break;
        }

        cmd = new DriveDistance(Constants.inchesToTicks(dist), 3);
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
