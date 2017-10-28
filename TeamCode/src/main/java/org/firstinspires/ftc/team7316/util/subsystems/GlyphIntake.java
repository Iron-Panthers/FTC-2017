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

    private boolean liftStopped;

    public GlyphIntake()
    {
        this.rightIntakeMotor = Hardware.instance.rightIntakeMotor;
        this.leftIntakeMotor = Hardware.instance.leftIntakeMotor;
        this.servo = Hardware.instance.intakeServo;
        this.intakeLiftMotor = Hardware.instance.intakeLiftMotor;

        liftStopped = false;
    }

    public void setServoPosition(double position) {
        this.servo.setPosition(position);
    }

    public void setIntakePower(double power) {
        this.rightIntakeMotor.setPower(-power);
        this.leftIntakeMotor.setPower(power);
    }

    public void setLiftPower(double power) {
        this.intakeLiftMotor.setPower(power);
    }

    public void maintainLiftPosition() {
        if(!liftStopped) {
            this.intakeLiftMotor.setTargetPosition(intakeLiftMotor.getCurrentPosition());
            this.intakeLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.intakeLiftMotor.setPower(0.2);
            liftStopped = true;
        }
    }

    public boolean getLiftStopped() { return liftStopped; }
    public void setLiftStopped(boolean stopped) { liftStopped = stopped; }

    @Override
    public Command defaultAutoCommand() {
        return new BlankCommand(this);
    }

    @Override
    public Command defaultTeleopCommand() {
        return new GlyphIntakeJoystick();
    }
}
