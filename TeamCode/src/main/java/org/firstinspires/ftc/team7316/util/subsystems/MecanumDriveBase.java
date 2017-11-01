package org.firstinspires.ftc.team7316.util.subsystems;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.PID;
import org.firstinspires.ftc.team7316.util.commands.BlankCommand;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveWithJoystick;

/**
 * Created by andrew on 9/12/17.
 */

public class MecanumDriveBase extends Subsystem {

    //all speed in ticks per second

    private PID fR_bL_PID; //front right and back left PID engine
    private double wantedFrBlSpeed = 0; // encoder ticks per second

    private PID fL_bR_PID; //front left and back right PID engine;
    private double wantedFlBrSpeed = 0; // encoder ticks per second

    private double wantedTurnSpeed = 0;

    private PID gyroPID;
    private double wantedOmega; //in degrees per second

    private long currentTime = 0;
    private long previousTime = 0; //in seconds

    private double weighting = 0.85;

    @Override
    public Command defaultAutoCommand() {
        return new BlankCommand(this);
    }

    @Override
    public Command defaultTeleopCommand() {
        return new DriveWithJoystick();
    }

    //setters
    public void setWantedOmega(double wantedOmega) {
        // something something wanted speed
        this.wantedOmega = wantedOmega; // degrees per second
    }

    //something something no pid here
    public void setWantedTurnSpeed(double wantedTurnSpeed) {
        if (wantedTurnSpeed > 0) {
            wantedTurnSpeed = (1 - Constants.TURNING_MOTOR_DEADZONE) * wantedTurnSpeed + Constants.TURNING_MOTOR_DEADZONE;
        } else {
            wantedTurnSpeed = (1 - Constants.TURNING_MOTOR_DEADZONE) * wantedTurnSpeed - Constants.TURNING_MOTOR_DEADZONE;
        }

        this.wantedTurnSpeed = wantedTurnSpeed;
    }

    private double strafingDeadzone(double wantedMovementAngle) {
        double maxDeadzone = Constants.STRAFING_MOTOR_DEADZONE - Constants.FORWARD_MOTOR_DEADZONE;
        double bufferOffset = Constants.FORWARD_MOTOR_DEADZONE;
        return Math.abs(maxDeadzone * Math.sin(wantedMovementAngle)) + bufferOffset;
    }

    public void setWantedSpeedAndMovementAngle(double wantedSpeed, double wantedMovementAngle) {

        Hardware.log("wantedSpeed", wantedSpeed);

        double strafingDeadzone = strafingDeadzone(wantedMovementAngle);
        wantedSpeed = (Constants.sqrt2 - strafingDeadzone) * wantedSpeed + strafingDeadzone;

        // this  T I D B I T  is for strafing
        double y = wantedSpeed * Math.cos(wantedMovementAngle);
        double x = wantedSpeed * Math.sin(wantedMovementAngle);

        double fL_bRpower = Constants.sqrt2 * (y + (x-y)/2); //length of da vector
        double fR_bLpower = -Constants.sqrt2 * ((x-y)/2); //again

        Hardware.log("front left back right", fL_bRpower);
        Hardware.log("front right back left", fR_bLpower);

        // ticks per second
        //wantedFrBlSpeed = (double)(Constants.DRIVE_RPM_MAX / 60 * Constants.ENCODER_TICK_PER_REV * fR_bLpower);
        //wantedFlBrSpeed = (double)(Constants.DRIVE_RPM_MAX / 60 * Constants.ENCODER_TICK_PER_REV * fL_bRpower);
        wantedFrBlSpeed = fR_bLpower;
        wantedFlBrSpeed = fL_bRpower;
    }

    public void driveWithSpeeds() {
        Hardware.instance.rightBackDriveMotor.setPower(weighting * (wantedFlBrSpeed - wantedTurnSpeed));
        Hardware.instance.leftFrontDriveMotor.setPower(weighting * (wantedFlBrSpeed + wantedTurnSpeed));
        Hardware.instance.rightFrontDriveMotor.setPower(weighting * (wantedFrBlSpeed - wantedTurnSpeed));
        Hardware.instance.leftBackDriveMotor.setPower(weighting * (wantedFrBlSpeed + wantedTurnSpeed));
    }

    public void stopMotors() {
        Hardware.instance.rightBackDriveMotor.setPower(0);
        Hardware.instance.leftFrontDriveMotor.setPower(0);
        Hardware.instance.rightFrontDriveMotor.setPower(0);
        Hardware.instance.leftBackDriveMotor.setPower(0);
    }

}