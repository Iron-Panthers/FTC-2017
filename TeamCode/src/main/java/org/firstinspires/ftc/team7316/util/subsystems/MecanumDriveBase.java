package org.firstinspires.ftc.team7316.util.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Util;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveWithJoystick;
import org.firstinspires.ftc.team7316.copypastaLib.MotionPath;

/**
 * Created by andrew on 9/12/17.
 */

public class MecanumDriveBase extends Subsystem {

    private Command driveWithJoystick;

    //all speed in ticks per second

    private double wantedFrBlSpeed = 0; // encoder ticks per second
    private double wantedFlBrSpeed = 0; // encoder ticks per second

    private double prevTurnSpeed = 0;
    private double targetHeading = 0;
    private final double GYRO_FEEDBACK_POWER_RATIO = 0.65;  // preliminary value

    private double wantedTurnSpeed = 0;
    private double wantedDriveAngle = 0;

    private double wantedOmega; //in degrees per second

    private double weighting = 0.9;

    public MecanumDriveBase() {}

    @Override
    public Command defaultAutoCommand() {
        return null;
    }

    @Override
    public Command defaultTeleopCommand() {
        if(driveWithJoystick == null) {
            driveWithJoystick = new DriveWithJoystick();
        }
        return driveWithJoystick;
    }

    @Override
    public void reset() {
        resetMotors();
    }

    //setters
    public void setWantedOmega(double wantedOmega) {
        // something something wanted speed
        this.wantedOmega = wantedOmega; // degrees per second
    }

    public void updateTargetHeading() {
        targetHeading = Hardware.instance.gyroWrapper.getHeading();
    }

    public void setWantedTurnSpeed(double wantedTurnSpeed) {
        if(wantedTurnSpeed == 0) {
            this.wantedTurnSpeed = 0;
            return;
        }

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
        this.wantedDriveAngle = wantedMovementAngle;

        double strafingDeadzone = strafingDeadzone(wantedMovementAngle);
        wantedSpeed = (Constants.SQRT2 - strafingDeadzone) * wantedSpeed + strafingDeadzone;

        // this  T I D B I T  is for strafing
        double y = wantedSpeed * Math.cos(wantedMovementAngle);
        double x = wantedSpeed * Math.sin(wantedMovementAngle);

        double fL_bRpower = Constants.SQRT2 * (y + (x-y)/2); //length of the vector
        double fR_bLpower = -Constants.SQRT2 * ((x-y)/2); //again

        Hardware.log("front left back right", fL_bRpower);
        Hardware.log("front right back left", fR_bLpower);

        fL_bRpower = Math.abs(fR_bLpower) < Constants.DRIVER_MOTOR_DEADZONE ? 0 : fL_bRpower;
        fR_bLpower = Math.abs(fR_bLpower) < Constants.DRIVER_MOTOR_DEADZONE ? 0 : fR_bLpower;

        // power
        wantedFrBlSpeed = fR_bLpower;
        wantedFlBrSpeed = fL_bRpower;
    }

    public void setMotorPID(double p, double i, double d, double f) {
        Hardware.instance.frontLeftDriveMotorWrapper.pid.setPID(p, i, d, f);
        Hardware.instance.frontRightDriveMotorWrapper.pid.setPID(p, i, d, f);
        Hardware.instance.backLeftDriveMotorWrapper.pid.setPID(p, i, d, f);
        Hardware.instance.backRightDriveMotorWrapper.pid.setPID(p, i, d, f);
    }

    public void setMotorTargets(int ticks) {
        Hardware.instance.frontLeftDriveMotorWrapper.setTargetTicks(ticks);
        Hardware.instance.frontRightDriveMotorWrapper.setTargetTicks(ticks);
        Hardware.instance.backLeftDriveMotorWrapper.setTargetTicks(ticks);
        Hardware.instance.backRightDriveMotorWrapper.setTargetTicks(ticks);
    }

    public void setMotorMaxSpeeds(double power) {
        Hardware.instance.frontLeftDriveMotorWrapper.setMaxPower(power);
        Hardware.instance.frontRightDriveMotorWrapper.setMaxPower(power);
        Hardware.instance.backLeftDriveMotorWrapper.setMaxPower(power);
        Hardware.instance.backRightDriveMotorWrapper.setMaxPower(power);
    }

    public void setMotorPaths(MotionPath path) {
        Hardware.instance.frontLeftDriveMotorWrapper.setPath(path);
        Hardware.instance.frontRightDriveMotorWrapper.setPath(path);
        Hardware.instance.backLeftDriveMotorWrapper.setPath(path);
        Hardware.instance.backRightDriveMotorWrapper.setPath(path);
    }

    public void strafeLeft(int ticks) {
        Hardware.instance.frontLeftDriveMotorWrapper.setTargetTicks(-ticks); //originally neg
        Hardware.instance.frontRightDriveMotorWrapper.setTargetTicks(ticks); //originally pos
        Hardware.instance.backLeftDriveMotorWrapper.setTargetTicks(ticks);//orginallt pos
        Hardware.instance.backRightDriveMotorWrapper.setTargetTicks(-ticks); //originally neg
    }

    public void strafeRight(int ticks) {
        Hardware.instance.frontLeftDriveMotorWrapper.setTargetTicks(ticks);
        Hardware.instance.frontRightDriveMotorWrapper.setTargetTicks(-ticks);
        Hardware.instance.backLeftDriveMotorWrapper.setTargetTicks(-ticks);
        Hardware.instance.backRightDriveMotorWrapper.setTargetTicks(ticks);
    }


//    public void setGyroTarget(double degrees) {
//        targetAngle = Hardware.instance.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle + degrees;
//    }

    public void resetMotorModes() {
        Hardware.instance.frontRightDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Hardware.instance.frontLeftDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Hardware.instance.backRightDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Hardware.instance.backLeftDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setBrakeMode(DcMotor.ZeroPowerBehavior mode) {
        Hardware.instance.frontLeftDriveMotor.setZeroPowerBehavior(mode);
        Hardware.instance.frontRightDriveMotor.setZeroPowerBehavior(mode);
        Hardware.instance.backLeftDriveMotor.setZeroPowerBehavior(mode);
        Hardware.instance.backRightDriveMotor.setZeroPowerBehavior(mode);
    }

    public void resetEncoders() {
        Hardware.instance.frontLeftDriveMotorWrapper.resetEncoder();
        Hardware.instance.frontRightDriveMotorWrapper.resetEncoder();
        Hardware.instance.backLeftDriveMotorWrapper.resetEncoder();
        Hardware.instance.backRightDriveMotorWrapper.resetEncoder();
    }

    public void resetMotors() {
        Hardware.instance.frontLeftDriveMotorWrapper.reset();
        Hardware.instance.frontRightDriveMotorWrapper.reset();
        Hardware.instance.backLeftDriveMotorWrapper.reset();
        Hardware.instance.backRightDriveMotorWrapper.reset();
    }

//
//    private double gyroError() {
//        double currentAngle = Hardware.instance.gyroWrapper.getHeading();
//
//        double dif = (targetAngle - currentAngle);
//
//        return Util.wrap(dif);
//    }

    public boolean completedDistance() {
        return Hardware.instance.frontLeftDriveMotorWrapper.completedDistance() && Hardware.instance.frontRightDriveMotorWrapper.completedDistance()
                && Hardware.instance.backLeftDriveMotorWrapper.completedDistance() && Hardware.instance.backRightDriveMotorWrapper.completedDistance();
    }

    private double pidToMotorPower(double out) {
        double absOut = Math.abs(out);
        double mult = (absOut/out); //1 if positive -1 if negative

        if (absOut > 1) {
            absOut = mult;
        } else if (absOut < Constants.FORWARD_MOTOR_DEADZONE) {
            absOut = Constants.FORWARD_MOTOR_DEADZONE * mult;
        } else {
            absOut = out;
        }

        return absOut;
    }

    //driving

    public void setMotors(double flPower, double frPower, double blPower, double brPower) {
        Hardware.instance.frontLeftDriveMotor.setPower(flPower);
        Hardware.instance.frontRightDriveMotor.setPower(frPower);
        Hardware.instance.backLeftDriveMotor.setPower(blPower);
        Hardware.instance.backRightDriveMotor.setPower(brPower);
    }

    public void setMotorPowers(double power) {
        setMotors(power, power, power, power);
    }

    public void turnMotors(double power) {
        setMotors(power, -power, power, -power);
    }

    public void driveWithPID() {
        Hardware.instance.frontLeftDriveMotorWrapper.setPowerPID();
        Hardware.instance.frontRightDriveMotorWrapper.setPowerPID();
        Hardware.instance.backLeftDriveMotorWrapper.setPowerPID();
        Hardware.instance.backRightDriveMotorWrapper.setPowerPID();
    }

    public void driveWithSpeeds() {
        if(!Hardware.gyro_offline) {
            if (wantedTurnSpeed == 0) {
                if (prevTurnSpeed != 0) {
                    updateTargetHeading();
                }
                //  it's {target - current} instead of {current - target} for error since it needs to be inverted
                wantedTurnSpeed += Util.wrap(targetHeading - Hardware.instance.gyroWrapper.getHeading()) * GYRO_FEEDBACK_POWER_RATIO;
            } else {
                updateTargetHeading();
            }
            prevTurnSpeed = wantedTurnSpeed;
        }
        setMotors(weighting * (wantedFlBrSpeed + wantedTurnSpeed),
                weighting * (wantedFrBlSpeed - wantedTurnSpeed),
                weighting * (wantedFrBlSpeed + wantedTurnSpeed),
                weighting * (wantedFlBrSpeed - wantedTurnSpeed));
    }

    public void stopMotors() {
        setMotorPowers(0);
    }
}