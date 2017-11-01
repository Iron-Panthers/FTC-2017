package org.firstinspires.ftc.team7316.util.subsystems;

import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
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

    private double wantedTurnSpeed = 0;

    private PID gyroPID;
    private double wantedOmega; //in degrees per second

    private long previousTime = 0;
    private long currentTime = 0;

    private double weighting = 0.9;

    public MecanumDriveBase() {
        fR_bL_PID = new PID(Constants.encoderP, Constants.encoderI, Constants.encoderD);
        fL_bR_PID = new PID(Constants.encoderP, Constants.encoderI, Constants.encoderD);
        gyroPID = new PID(Constants.gyroP, Constants.gyroI, Constants.gyroD);

        previousTime = System.currentTimeMillis() / 1000;
        currentTime = (System.currentTimeMillis() + 1) / 1000; //xd
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

        double fL_bRpower = Constants.sqrt2 * (y + (x-y)/2); //length of da vector
        double fR_bLpower = -Constants.sqrt2 * ((x-y)/2); //again

        Hardware.log("front left back right", fL_bRpower);
        Hardware.log("front right back left", fR_bLpower);

        // power
        wantedFrBlSpeed = fR_bLpower;
        wantedFlBrSpeed = fL_bRpower;
    }

    public void setMotorTargets(int ticks) {
        targetFlTicks = Hardware.instance.frontLeftDriveMotor.getCurrentPosition() - ticks;
        targetFrTicks = Hardware.instance.frontRightDriveMotor.getCurrentPosition() + ticks;
        targetBlTicks = Hardware.instance.backLeftDriveMotor.getCurrentPosition() - ticks;
        targetBrTicks = Hardware.instance.backRightDriveMotor.getCurrentPosition() + ticks;
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
        //New gyro has some cool beans return types

        //Position angle = Hardware.instance.gyro.getPosition();
        //double currentangle = angle.z;
        AngularVelocity angVelocity = Hardware.instance.gyro.getAngularVelocity(); //radians per second apparently
        double currentOmega = angVelocity.zRotationRate;
        currentOmega *= 180 / Math.PI;

        double dif = (wantedOmega - currentOmega);

        //return Util.wrap(dif);
        return 0;
    }

    private boolean withinError(int target, int error) {
        return Math.abs(error) < Math.abs(target * Constants.DISTANCE_ERROR_RANGE);
    }

    public boolean completedDistance() {
        //fix this please
        return (withinError(targetFrTicks, fR_Error()) && withinError(targetFlTicks, fL_Error()))
                || (withinError(targetBrTicks, bR_Error()) && withinError(targetBlTicks, bL_Error()));
    }

    private double pidToMotorPower(double out) {
        double absOut = Math.abs(out);
        double mult = (absOut/out); //1 if positive -1 if negative

        if (absOut > 1) {
            absOut = mult;
        } else if (absOut < Constants.DRIVER_MOTOR_DEADZONE) {
            absOut = Constants.DRIVER_MOTOR_DEADZONE * mult;
        } else {
            absOut = out;
        }

        return absOut;
    }

    //driving
    public void driveWithSpeedsPID(double power) {

        currentTime = System.currentTimeMillis() / 1000;
        double deltaT = currentTime - previousTime;

        double fR_bL_Pwr = pidToMotorPower(this.fR_bL_PID.newError(((fR_Error() + bL_Error()) / 2), deltaT));
        double fL_bR_Pwr = pidToMotorPower(this.fL_bR_PID.newError(((fL_Error() + bR_Error()) / 2), deltaT));

        double gyro_Pwr = pidToMotorPower(this.gyroPID.newError(gyroError(), deltaT));

        previousTime = currentTime;

        Hardware.instance.backRightDriveMotor.setPower(power * weighting * (fL_bR_Pwr + gyro_Pwr));
        Hardware.instance.frontLeftDriveMotor.setPower(power * weighting * (fL_bR_Pwr - gyro_Pwr));
        Hardware.instance.frontRightDriveMotor.setPower(power * weighting * (fR_bL_Pwr + gyro_Pwr));
        Hardware.instance.backLeftDriveMotor.setPower(power * weighting * (fR_bL_Pwr - gyro_Pwr));
    }

    public void driveWithSpeeds() {
        Hardware.instance.backRightDriveMotor.setPower(weighting * (wantedFlBrSpeed + wantedTurnSpeed));
        Hardware.instance.frontLeftDriveMotor.setPower(weighting * (wantedFlBrSpeed - wantedTurnSpeed));
        Hardware.instance.frontRightDriveMotor.setPower(weighting * (wantedFrBlSpeed + wantedTurnSpeed));
        Hardware.instance.backLeftDriveMotor.setPower(weighting * (wantedFrBlSpeed - wantedTurnSpeed));
    }

    public void stopMotors() {
        Hardware.instance.backRightDriveMotor.setPower(0);
        Hardware.instance.frontLeftDriveMotor.setPower(0);
        Hardware.instance.frontRightDriveMotor.setPower(0);
        Hardware.instance.backLeftDriveMotor.setPower(0);
    }

}