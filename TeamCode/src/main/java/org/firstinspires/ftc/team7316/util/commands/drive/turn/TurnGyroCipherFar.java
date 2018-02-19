package org.firstinspires.ftc.team7316.util.commands.drive.turn;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by jerry on 2/17/18.
 */

public class TurnGyroCipherFar extends Command {

    //  in degrees

    private Alliance alliance;

    private TurnGyroPID turnCommand;

    public TurnGyroCipherFar(Alliance a) {
        alliance = a;
    }

    @Override
    public void init() {
        switch (alliance) {
            case RED:
                switch (Hardware.instance.vuforiaCameraWrapper.vuMark) {
                    case LEFT:
                        turnCommand = new TurnGyroPID(-Constants.FAR_COLUMN_ANGLE_FAR);
                        break;
                    case RIGHT:
                        turnCommand = new TurnGyroPID(-Constants.CLOSE_COLUMN_ANGLE_FAR);
                        break;
                    default:
                        turnCommand = new TurnGyroPID(-Constants.MIDDLE_COLUMN_ANGLE_FAR);
                        break;
                }
                break;
            case BLUE:
                switch (Hardware.instance.vuforiaCameraWrapper.vuMark) {
                    case LEFT:
                        turnCommand = new TurnGyroPID(Constants.CLOSE_COLUMN_ANGLE_FAR);
                        break;
                    case RIGHT:
                        turnCommand = new TurnGyroPID(Constants.FAR_COLUMN_ANGLE_FAR);
                        break;
                    default:
                        turnCommand = new TurnGyroPID(Constants.MIDDLE_COLUMN_ANGLE_FAR);
                        break;
                }
                break;
        }

        turnCommand.init();
    }

    @Override
    public void loop() {
        turnCommand.loop();
    }

    @Override
    public boolean shouldRemove() {
        return turnCommand.shouldRemove();
    }

    @Override
    protected void end() {
        turnCommand.interrupt();
    }
}
