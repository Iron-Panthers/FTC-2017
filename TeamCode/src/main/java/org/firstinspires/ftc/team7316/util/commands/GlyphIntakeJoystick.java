package org.firstinspires.ftc.team7316.util.commands;

/**
 * Created by andrew on 10/23/17.
 */

import org.firstinspires.ftc.team7316.util.commands.*;

public class GlyphIntakeJoystick extends Command {

    public GlyphIntakeJoystick() {

    }

    @Override
    public void init() {
        this.servo.setPosition(0);
    }

    @Override
    public void loop() {
        this.rightIntakeMotor.setPower(-leftJoystick.getY());
        this.leftIntakeMotor.setPower(leftJoystick.getY());
        this.servo.setPosition(rightJoystick.getX());
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void end() {
        this.rightIntakeMotor.setPower(0);
        this.leftIntakeMotor.setPower(0);
    }

}
