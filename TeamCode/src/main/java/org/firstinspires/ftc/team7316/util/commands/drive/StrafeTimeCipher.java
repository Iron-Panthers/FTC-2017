package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by jerry on 2/5/18.
 */

public class StrafeTimeCipher extends Command {

    private Alliance alliance;
    private DriveForTime cmd;

    public StrafeTimeCipher(Alliance a) {
        alliance = a;
    }

    @Override
    public void init() {
        double piOver180 = Math.PI / 180;
        switch (alliance) {
            case RED:
                switch (Hardware.instance.vuforiaCameraWrapper.vuMark) {
                    case LEFT:
                        cmd = new DriveForTime(0.6, Constants.FAR_COLUMN_ANGLE_FAR * piOver180, 2);
                    case CENTER:
                        cmd = new DriveForTime(0.6, Constants.MIDDLE_COLUMN_ANGLE_FAR * piOver180, 2);
                    case RIGHT:
                        cmd = new DriveForTime(0.6, Constants.CLOSE_COLUMN_ANGLE_FAR * piOver180, 2);
                    case UNKNOWN:
                        cmd = new DriveForTime(0.6, Constants.MIDDLE_COLUMN_ANGLE_FAR * piOver180, 2);
                }
            case BLUE:
                switch (Hardware.instance.vuforiaCameraWrapper.vuMark) {
                    case LEFT:
                        cmd = new DriveForTime(0.6, Constants.CLOSE_COLUMN_ANGLE_FAR * piOver180, 2);
                    case CENTER:
                        cmd = new DriveForTime(0.6, Constants.MIDDLE_COLUMN_ANGLE_FAR * piOver180, 2);
                    case RIGHT:
                        cmd = new DriveForTime(0.6, Constants.FAR_COLUMN_ANGLE_FAR * piOver180, 2);
                    case UNKNOWN:
                        cmd = new DriveForTime(0.6, Constants.MIDDLE_COLUMN_ANGLE_FAR * piOver180, 2);
                }
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
        cmd.end();
    }
}
