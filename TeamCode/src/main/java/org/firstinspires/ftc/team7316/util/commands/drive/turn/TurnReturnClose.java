package org.firstinspires.ftc.team7316.util.commands.drive.turn;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Determines which angle to turn to so it can drive to a new cryptobox column
 * Intakes at the same time to bring up glyphs
 *
 * Created by jerry on 2/18/18.
 */

public class TurnReturnClose extends Command {

    private TurnGyroPID cmd;

    @Override
    public void init() {
        double targetangle = 90;
        switch (Hardware.instance.vuforiaCameraWrapper.vuMark) {
            case LEFT:
                targetangle += Constants.MultiglyphRotate.LEFT.degrees;
            case RIGHT:
                targetangle -= Constants.MultiglyphRotate.RIGHT.degrees;
            default:
                targetangle += Constants.MultiglyphRotate.CENTER.degrees;
        }

        cmd = new TurnGyroPID(targetangle, 3, 120);
        cmd.init();
    }

    @Override
    public void loop() {
        cmd.loop();
        Subsystems.instance.glyphIntake.setIntakePower(-0.7, -0.7);
    }

    @Override
    public boolean shouldRemove() {
        return cmd.shouldRemove();
    }

    @Override
    protected void end() {
        cmd.interrupt();
        Subsystems.instance.glyphIntake.setIntakePower(0, 0);
    }
}
