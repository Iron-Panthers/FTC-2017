package org.firstinspires.ftc.team7316.util.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.BlankCommand;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.commands.intake.ClampIntake;
import org.firstinspires.ftc.team7316.util.commands.intake.GlyphIntakeJoystick;

/**
 * Created by jerry on 10/11/17.
 */

public class GlyphIntake extends Subsystem {

    private Servo servo;
    private DcMotor rightIntakeMotor;
    private DcMotor leftIntakeMotor;
    private DcMotor intakeLiftMotor;

    private boolean liftStopped;

    public final double liftLowerLimit = 1510; //not even the low number lul
    public final double liftUpperLimit = -9913; //actually the smaller number because reverse

    public GlyphIntake()
    {
        this.rightIntakeMotor = Hardware.instance.rightIntakeMotor;
        this.leftIntakeMotor = Hardware.instance.leftIntakeMotor;
        this.servo = Hardware.instance.intakeServo;
        this.intakeLiftMotor = Hardware.instance.intakeLiftMotor;

        liftStopped = false;
    }

    public void setServoPosition(double position) {
        servo.scaleRange(0, 1);
        this.servo.setPosition(position);
    }

    public void setServoPositionScaled(double position) {
        servo.scaleRange(Constants.INTAKE_SERVO_MIN_POSITION, Constants.INTAKE_SERVO_MAX_POSITION);

        this.servo.setPosition(position);
    }

    public void setIntakePower(double power) {
        this.rightIntakeMotor.setPower(-power);
        this.leftIntakeMotor.setPower(power);
    }

    public void setLiftPower(double power) {
        this.intakeLiftMotor.setPower(power);
    }

    public boolean getLiftStopped() { return liftStopped; }
    public void setLiftStopped(boolean stopped) { liftStopped = stopped; }

    @Override
    public Command defaultAutoCommand() {
        return new ClampIntake();
    }

    @Override
    public Command defaultTeleopCommand() {
        return new GlyphIntakeJoystick();
    }
}
