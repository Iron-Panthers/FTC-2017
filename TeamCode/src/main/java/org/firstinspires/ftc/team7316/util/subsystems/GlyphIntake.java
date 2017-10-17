package org.firstinspires.ftc.team7316.util.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;
import org.firstinspires.ftc.team7316.util.hardware.ServoWrapper;
import org.firstinspires.ftc.team7316.util.input.JoystickWrapper;

/**
 * Created by jerry on 10/11/17.
 */

public class GlyphIntake implements Loopable {

    private Servo servo;
    private DcMotor rightIntakeMotor;
    private DcMotor leftIntakeMotor;
    private JoystickWrapper joystick;

    public GlyphIntake(Servo servo, DcMotor rightIntakeMotor, DcMotor leftIntakeMotor, JoystickWrapper joystick)
    {
        this.joystick = joystick;
        this.rightIntakeMotor = rightIntakeMotor;
        this.leftIntakeMotor = leftIntakeMotor;
        this.servo = servo;
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        this.rightIntakeMotor.setPower(joystick.getY());
        this.leftIntakeMotor.setPower(-joystick.getY());
        this.servo.setPosition(joystick.getX());
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }
}
