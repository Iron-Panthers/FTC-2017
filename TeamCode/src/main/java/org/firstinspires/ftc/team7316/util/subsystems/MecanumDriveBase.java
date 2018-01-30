package org.firstinspires.ftc.team7316.util.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveWithJoystick;
import org.firstinspires.ftc.team7316.copypastaLib.MotionPath;

/**
 * Created by andrew on 9/12/17.
 */

public class MecanumDriveBase extends Subsystem {

    private Command driveWithJoystick = new DriveWithJoystick();

    //all speed in ticks per second

    private double wantedFrBlSpeed = 0; // encoder ticks per second

    private double wantedFlBrSpeed = 0; // encoder ticks per second

    private double targetAngle = 0;

    private double wantedTurnSpeed = 0;

    private double wantedOmega; //in degrees per second

    private double weighting = 0.9;

    public MecanumDriveBase() {

    }

    @Override
    public Command defaultAutoCommand() {
        return null;
    }

    @Override
    public Command defaultTeleopCommand() {
        return driveWithJoystick;
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

        double fL_bRpower = Constants.sqrt2 * (y + (x-y)/2); //length of the vector
        double fR_bLpower = -Constants.sqrt2 * ((x-y)/2); //again

        Hardware.log("front left back right", fL_bRpower);
        Hardware.log("front right back left", fR_bLpower);

        fL_bRpower = Math.abs(fR_bLpower) < Constants.DRIVER_MOTOR_DEADZONE ? 0 : fL_bRpower;
        fR_bLpower = Math.abs(fR_bLpower) < Constants.DRIVER_MOTOR_DEADZONE ? 0 : fR_bLpower;

        // power
        wantedFrBlSpeed = fR_bLpower;
        wantedFlBrSpeed = fL_bRpower;
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
//        targetAngle = Hardware.instance.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle + degrees;
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

    public void setMotorPowers(double power) {
        Hardware.instance.backRightDriveMotor.setPower(power);
        Hardware.instance.frontLeftDriveMotor.setPower(power);
        Hardware.instance.frontRightDriveMotor.setPower(power);
        Hardware.instance.backLeftDriveMotor.setPower(power);
    }

    public void turnMotors(double power) {
        Hardware.instance.backRightDriveMotor.setPower(-power);
        Hardware.instance.frontLeftDriveMotor.setPower(power);
        Hardware.instance.frontRightDriveMotor.setPower(-power);
        Hardware.instance.backLeftDriveMotor.setPower(power);
    }

    public void driveWithSpeedsPID() {
        Hardware.instance.frontLeftDriveMotorWrapper.setPowerPID();
        Hardware.instance.frontRightDriveMotorWrapper.setPowerPID();
        Hardware.instance.backLeftDriveMotorWrapper.setPowerPID();
        Hardware.instance.backRightDriveMotorWrapper.setPowerPID();
    }

    public void driveWithSpeeds() {
        Hardware.instance.backRightDriveMotor.setPower(weighting * (wantedFlBrSpeed - wantedTurnSpeed));
        Hardware.instance.frontLeftDriveMotor.setPower(weighting * (wantedFlBrSpeed + wantedTurnSpeed));
        Hardware.instance.frontRightDriveMotor.setPower(weighting * (wantedFrBlSpeed - wantedTurnSpeed));
        Hardware.instance.backLeftDriveMotor.setPower(weighting * (wantedFrBlSpeed + wantedTurnSpeed));
    }

    public void stopMotors() {
        setMotorPowers(0);
    }

}