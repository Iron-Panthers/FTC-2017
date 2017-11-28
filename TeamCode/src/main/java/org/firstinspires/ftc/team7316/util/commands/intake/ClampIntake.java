package org.firstinspires.ftc.team7316.util.commands.intake;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 11/18/17.
 */

public class ClampIntake extends Command {

    public ClampIntake() {

    }

    @Override
    public void init() {
        Hardware.instance.intakeServo.scaleRange(0, 1);
        Subsystems.instance.glyphIntake.setServoPosition(Constants.INTAKE_SERVO_MAX_POSITION);
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {

    }
}