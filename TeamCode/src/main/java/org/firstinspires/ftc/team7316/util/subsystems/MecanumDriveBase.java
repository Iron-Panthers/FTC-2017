package org.firstinspires.ftc.team7316.util.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.PID;
import org.firstinspires.ftc.team7316.util.Util;
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

    private int targetFlTicks = 0;
    private int targetFrTicks = 0;
    private int targetBlTicks = 0;
    private int targetBrTicks = 0;
    private double targetAngle = 0;

    private double wantedTurnSpeed = 0;

    private PID gyroPID;
    private double wantedOmega; //in degrees per second

    private double previousTime = 0;

    private double weighting = 0.9;

    public MecanumDriveBase() {
        fR_bL_PID = new PID(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D);
        fL_bR_PID = new PID(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D);
        gyroPID = new PID(Constants.GYRO_P, Constants.GYRO_I, Constants.GYRO_D);
    }

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

        double fL_bRpower = Constants.sqrt2 * (y + (x-y)/2); //length of the vector
        double fR_bLpower = -Constants.sqrt2 * ((x-y)/2); //again

        Hardware.log("front left back right", fL_bRpower);
        Hardware.log("front right back left", fR_bLpower);

        // power
        wantedFrBlSpeed = fR_bLpower;
        wantedFlBrSpeed = fL_bRpower;
    }

    public void setMotorTargets(int ticks) {
        targetFlTicks = Hardware.instance.frontLeftDriveMotor.getCurrentPosition() + ticks;
        targetFrTicks = Hardware.instance.frontRightDriveMotor.getCurrentPosition() + ticks;
        targetBlTicks = Hardware.instance.backLeftDriveMotor.getCurrentPosition() + ticks;
        targetBrTicks = Hardware.instance.backRightDriveMotor.getCurrentPosition() + ticks;
    }

    public void setGyroTarget(double degrees) {
        targetAngle = Hardware.instance.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle + degrees;
    }

    public void resetPrevioustime() {
        previousTime = System.currentTimeMillis() / 1000;
    }

    public void resetMotorModes() {
        Hardware.instance.frontRightDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Hardware.instance.frontLeftDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Hardware.instance.backRightDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Hardware.instance.backLeftDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setMotorModeDistance() {
        Hardware.instance.frontRightDriveMotor.setTargetPosition(targetFrTicks);
        Hardware.instance.frontLeftDriveMotor.setTargetPosition(targetFlTicks);
        Hardware.instance.backRightDriveMotor.setTargetPosition(targetBrTicks);
        Hardware.instance.backLeftDriveMotor.setTargetPosition(targetBlTicks);

        Hardware.instance.frontRightDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.instance.frontLeftDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.instance.backRightDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.instance.backLeftDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    //errors
    private int fR_Error() {
        int fRError = Hardware.instance.frontRightDriveMotor.getCurrentPosition();

        return targetFrTicks - fRError;
    }

    private int fL_Error() {
        int fLError = Hardware.instance.frontRightDriveMotor.getCurrentPosition();

        return targetFlTicks - fLError;
    }

    private int bR_Error() {
        int bRError = Hardware.instance.backLeftDriveMotor.getCurrentPosition();

        return targetBrTicks - bRError;
    }

    private int bL_Error() {
        int bLError = Hardware.instance.backLeftDriveMotor.getCurrentPosition();

        return targetBlTicks - bLError;
    }

    private double gyroError() {
        double currentAngle = Hardware.instance.gyroWrapper.getHeading();

        double dif = (targetAngle - currentAngle);

        return Util.wrap(dif);
    }

    private boolean withinError(int target, int error) {
        return Math.abs(target - error) < Constants.DISTANCE_ERROR_RANGE_TICKS;
    }

    public boolean completedDistance() {
        return (withinError(targetFrTicks, fR_Error()) && withinError(targetFlTicks, fL_Error()))
           && (withinError(targetBrTicks, bR_Error()) && withinError(targetBlTicks, bL_Error()));
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
    public void runMotorsDistance() {
        int error = targetFlTicks - Hardware.instance.frontLeftDriveMotor.getCurrentPosition();
        double power = Constants.DRIVE_P * (double)error;
        Hardware.instance.backRightDriveMotor.setPower(power);
        Hardware.instance.frontLeftDriveMotor.setPower(power);
        Hardware.instance.frontRightDriveMotor.setPower(power);
        Hardware.instance.backLeftDriveMotor.setPower(power);
        Hardware.log("flerror", targetFlTicks - Hardware.instance.frontLeftDriveMotor.getCurrentPosition());
    }

    public void turnMotors(double power) {
        Hardware.instance.backRightDriveMotor.setPower(-power);
        Hardware.instance.frontLeftDriveMotor.setPower(power);
        Hardware.instance.frontRightDriveMotor.setPower(-power);
        Hardware.instance.backLeftDriveMotor.setPower(power);
    }

    public void driveWithSpeedsPID(double power) {

        double currentTime = System.currentTimeMillis() / 1000;
        double deltaT = currentTime - previousTime;

        double fR_bL_Pwr = pidToMotorPower(this.fR_bL_PID.newError(((fR_Error() + bL_Error()) / 2), deltaT));
        double fL_bR_Pwr = pidToMotorPower(this.fL_bR_PID.newError(((fL_Error() + bR_Error()) / 2), deltaT));

        double gyro_Pwr = pidToMotorPower(this.gyroPID.newError(gyroError(), deltaT));

        Hardware.log("fR_error", fR_Error());
        Hardware.log("fL_error", fL_Error());
        Hardware.log("bR_error", bR_Error());
        Hardware.log("bL_error", bL_Error());
        Hardware.log("fR_bL_Pwr", fR_bL_Pwr);
        Hardware.log("fL_bR_Pwr", fL_bR_Pwr);

        previousTime = currentTime;
        Hardware.instance.backRightDriveMotor.setPower(power * (fL_bR_Pwr - gyro_Pwr));
        Hardware.instance.frontLeftDriveMotor.setPower(power * (fL_bR_Pwr + gyro_Pwr));
        Hardware.instance.frontRightDriveMotor.setPower(power * (fR_bL_Pwr - gyro_Pwr));
        Hardware.instance.backLeftDriveMotor.setPower(power * (fR_bL_Pwr + gyro_Pwr));
    }

    public void driveWithSpeeds() {
        Hardware.instance.backRightDriveMotor.setPower(weighting * (wantedFlBrSpeed - wantedTurnSpeed));
        Hardware.instance.frontLeftDriveMotor.setPower(weighting * (wantedFlBrSpeed + wantedTurnSpeed));
        Hardware.instance.frontRightDriveMotor.setPower(weighting * (wantedFrBlSpeed - wantedTurnSpeed));
        Hardware.instance.backLeftDriveMotor.setPower(weighting * (wantedFrBlSpeed + wantedTurnSpeed));
    }

    public void stopMotors() {
        Hardware.instance.backRightDriveMotor.setPower(0);
        Hardware.instance.frontLeftDriveMotor.setPower(0);
        Hardware.instance.frontRightDriveMotor.setPower(0);
        Hardware.instance.backLeftDriveMotor.setPower(0);
    }

}