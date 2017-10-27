package org.firstinspires.ftc.team7316.util.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.BlankCommand;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.commands.GlyphIntakeJoystick;

/**
 * Created by jerry on 10/11/17.
 */

public class GlyphIntake extends Subsystem {

    private Servo servo;
    private DcMotor rightIntakeMotor;
    private DcMotor leftIntakeMotor;
    private DcMotor intakeLiftMotor;


    public GlyphIntake()
    {
        this.rightIntakeMotor = Hardware.instance.rightIntakeMotor;
        this.leftIntakeMotor = Hardware.instance.leftIntakeMotor;
        this.servo = Hardware.instance.intakeServo;
        this.intakeLiftMotor = Hardware.instance.intakeLiftMotor;

        this.servo.scaleRange(Constants.INTAKE_SERVO_MIN_POSITION, Constants.INTAKE_SERVO_MAX_POSITION);
    }

    public void setPosition(double position) {
        this.servo.setPosition(position);
    }

    public void setIntakePower(double power) {
        this.rightIntakeMotor.setPower(-power);
        this.leftIntakeMotor.setPower(power);
    }

    public void setLiftPower(boolean up) {
        if(up) {
            this.intakeLiftMotor.setPower(Constants.INTAKE_LIFT_POWER);
        } else {
            this.intakeLiftMotor.setPower(-Constants.INTAKE_LIFT_POWER);
        }
    }

    @Override
    public Command defaultAutoCommand() {
        return new BlankCommand(this);
    }

    @Override
    public Command defaultTeleopCommand() {
        return new GlyphIntakeJoystick();
    }
}
