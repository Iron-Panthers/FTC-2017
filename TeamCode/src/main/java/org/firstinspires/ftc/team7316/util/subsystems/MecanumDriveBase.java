package org.firstinspires.ftc.team7316.util.subsystems;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
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

    private double wantedTurnSpeed = 0;

    private PID gyroPID;
    private double wantedOmega; //in degrees per second

    private long currentTime;
    private long previousTime; //in seconds

    private double weighting = 0.85;

    public MecanumDriveBase() {
        fR_bL_PID = new PID(Constants.encoderP, Constants.encoderI, Constants.encoderD);
        fL_bR_PID = new PID(Constants.encoderP, Constants.encoderI, Constants.encoderD);

        previousTime = System.currentTimeMillis() / 1000;
        currentTime = (System.currentTimeMillis() + 1) / 1000; //there was an attempt to prevent divbyzero
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

        // ticks per second
        wantedFrBlSpeed = Constants.DRIVE_RPM_MAX / 60 * Constants.ENCODER_TICK_PER_REV * fR_bLpower;
        wantedFlBrSpeed = Constants.DRIVE_RPM_MAX / 60 * Constants.ENCODER_TICK_PER_REV * fL_bRpower;
    }

    //errors
    private double fR_bL_Error() {
        int fRError = Hardware.instance.rightFrontDriveMotor.getCurrentPosition();
        int bLError = Hardware.instance.leftBackDriveMotor.getCurrentPosition();

        double average = (fRError + bLError) / (2 * (currentTime - previousTime));
        return this.wantedFrBlSpeed - average; // ticks per second
    }

    private double fL_bR_Error() {
        int fLError = Hardware.instance.leftFrontDriveMotor.getCurrentPosition();
        int bRError = Hardware.instance.rightBackDriveMotor.getCurrentPosition();

        double average = (fLError + bRError) / (2 * (currentTime - previousTime));
        return this.wantedFlBrSpeed - average; // ticks per second
    }

    private double gyroError() {
        //New gyro has some cool beans return types

        //Position angle = Hardware.instance.gyro.getPosition();
        //double currentangle = angle.z;
        AngularVelocity angVelocity = Hardware.instance.gyro.getAngularVelocity(); //radians per second apparently
        double currentOmega = angVelocity.zRotationRate;
        currentOmega *= 180 / Math.PI;

        double dif = (wantedOmega - currentOmega) / (currentTime - previousTime); // degrees per second

        return Util.wrap(dif);
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
    public void driveWithSpeeds() {

        currentTime = System.currentTimeMillis() / 1000;

        //setWantedSpeedAndMovementAngle(some_speed, some_angle);
        //setWantedAngle(some_angle);

        double fR_bL_Pwr = pidToMotorPower(this.fR_bL_PID.newError(fR_bL_Error()));
        double fL_bR_Pwr = pidToMotorPower(this.fL_bR_PID.newError(fL_bR_Error()));

        double gyro_Pwr = pidToMotorPower(this.gyroPID.newError(gyroError()));

        previousTime = currentTime;

        Hardware.instance.rightBackDriveMotor.setPower(weighting * (fL_bR_Pwr + gyro_Pwr));
        Hardware.instance.leftFrontDriveMotor.setPower(weighting * (fL_bR_Pwr - gyro_Pwr));
        Hardware.instance.rightFrontDriveMotor.setPower(weighting * (fR_bL_Pwr + gyro_Pwr));
        Hardware.instance.leftBackDriveMotor.setPower(weighting * (fR_bL_Pwr - gyro_Pwr));
    }

    public void stopMotors() {
        Hardware.instance.rightBackDriveMotor.setPower(0);
        Hardware.instance.leftFrontDriveMotor.setPower(0);
        Hardware.instance.rightFrontDriveMotor.setPower(0);
        Hardware.instance.leftBackDriveMotor.setPower(0);
    }

}