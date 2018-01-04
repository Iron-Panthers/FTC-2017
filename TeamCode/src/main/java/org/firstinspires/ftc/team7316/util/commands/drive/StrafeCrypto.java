package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by jerry on 1/3/18.
 */

public class StrafeCrypto extends Command {

    private Alliance alliance;
    private DriveForTime strafecommand;

    public StrafeCrypto(Alliance a) {
        alliance = a;
    }

    @Override
    public void init() {
        //rename the dumbass constants later
        switch (alliance) {
            case RED:
                switch (Hardware.instance.vuforiaCameraWrapper.vuMark) {
                    case LEFT:
                        strafecommand = new DriveForTime(Constants.LEFT_POWER_TIME, -Math.PI/2, Constants.STRAFE_CRYPTO_LEFT_TIME);
                        break;
                    case CENTER:
                        strafecommand = new DriveForTime(Constants.LEFT_POWER_TIME, -Math.PI/2, Constants.STRAFE_CRYPTO_CENTER_TIME);
                        break;
                    case RIGHT:
                        strafecommand = new DriveForTime(Constants.LEFT_POWER_TIME, -Math.PI/2, Constants.STRAFE_CRYPTO_RIGHT_TIME);
                        break;
                    default:
                        strafecommand = new DriveForTime(Constants.LEFT_POWER_TIME, -Math.PI/2, Constants.STRAFE_CRYPTO_CENTER_TIME);
                        break;
                }
                break;
            case BLUE:
                switch (Hardware.instance.vuforiaCameraWrapper.vuMark) {
                    //yes it's confusing no im not going to fix it
                    case LEFT:
                        strafecommand = new DriveForTime(Constants.LEFT_POWER_TIME, -Math.PI/2, Constants.STRAFE_CRYPTO_RIGHT_TIME);
                        break;
                    case CENTER:
                        strafecommand = new DriveForTime(Constants.LEFT_POWER_TIME, -Math.PI/2, Constants.STRAFE_CRYPTO_CENTER_TIME);
                        break;
                    case RIGHT:
                        strafecommand = new DriveForTime(Constants.LEFT_POWER_TIME, -Math.PI/2, Constants.STRAFE_CRYPTO_LEFT_TIME);
                        break;
                    default:
                        strafecommand = new DriveForTime(Constants.LEFT_POWER_TIME, -Math.PI/2, Constants.STRAFE_CRYPTO_CENTER_TIME);
                        break;
                }
                break;
        }
        strafecommand.init();
    }

    @Override
    public void loop() {
        strafecommand.loop();
    }

    @Override
    public boolean shouldRemove() {
        return strafecommand.shouldRemove();
    }

    @Override
    protected void end() {
        strafecommand.end();
    }
}
