package org.firstinspires.ftc.team7316.util.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;
import org.firstinspires.ftc.team7316.util.hardware.ServoWrapper;
import org.firstinspires.ftc.team7316.util.input.JoystickWrapper;

/**
 * Created by jerry on 10/11/17.
 */

public class GlyphIntake extends Subsystem {

    private Servo servo;
    private DcMotor rightIntakeMotor;
    private DcMotor leftIntakeMotor;
    private JoystickWrapper leftJoystick;
    private JoystickWrapper rightJoystick;

    public GlyphIntake(Servo servo, DcMotor rightIntakeMotor, DcMotor leftIntakeMotor, JoystickWrapper leftJoystick, JoystickWrapper rightJoystick)
    {
        this.leftJoystick = leftJoystick;
        this.rightJoystick = rightJoystick;
        this.rightIntakeMotor = rightIntakeMotor;
        this.leftIntakeMotor = leftIntakeMotor;
        this.servo = servo;

        this.servo.scaleRange(Constants.INTAKE_SERVO_MIN_POSITION, Constants.INTAKE_SERVO_MAX_POSITION);
    }


    @Override
    public Command defaultAutoCommand() {
        return null;
    }

    @Override
    public Command defaultTeleopCommand() {
        return null;
    }
}
