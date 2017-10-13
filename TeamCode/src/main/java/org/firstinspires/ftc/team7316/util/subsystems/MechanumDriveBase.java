package org.firstinspires.ftc.team7316.util.subsystems;

import com.qualcomm.robotcore.eventloop.SyncdDevice;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.PID;
import org.firstinspires.ftc.team7316.util.Util;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 9/12/17.
 */

public class MechanumDriveBase implements Loopable {

    //all speed in ticks per second

    private PID fR_bL_PID; //front right and back left PID engine
    private double wantedFrBlSpeed = 0; // encoder ticks per second

    private PID fL_bR_PID; //front left and back right PID engine;
    private double wantedFlBrSpeed = 0; // encoder ticks per second

    private PID gyroPID;
    private double wantedOmega; //in degrees per second

    private long currentTime = 0;
    private long previousTime = 0; //in seconds

    private double weighting = 0.7;

    @Override
    public void init() {
        this.fR_bL_PID = new PID(Constants.encoderP, Constants.encoderI, Constants.encoderD);
        this.fL_bR_PID = new PID(Constants.encoderP, Constants.encoderI, Constants.encoderD);
        this.gyroPID = new PID(Constants.gyroP, Constants.gyroI, Constants.gyroD);

        //this.wantedFlBrSpeed = fL_bR_Error();
        //this.wantedFrBlSpeed = fR_bL_Error();

        this.previousTime = System.currentTimeMillis() / 1000;
    }

    //setters
    public void setWantedOmega(double wantedOmega) {
        // something something wanted speed
        this.wantedOmega = wantedOmega; // degrees per second
    }

    public void setWantedSpeedAndMovementAngle(double wantedSpeed, double wantedMovementAngle) {
        // this  T I D B I T  is for strafing
        double x = wantedSpeed * Math.cos(wantedMovementAngle * Math.PI / 180);
        double y = wantedSpeed * Math.sin(wantedMovementAngle * Math.PI / 180);

        double fR_bLpower = Constants.sqrt2 * (y + (x-y)/2); //length of da vector
        double fL_bRpower = Constants.sqrt2 * ((x-y)/2); //again

        // ticks per second
        wantedFrBlSpeed = (double)(Constants.DRIVE_RPM_MAX / 60 * Constants.ENCODER_TICK_PER_REV * fR_bLpower);
        wantedFlBrSpeed = (double)(Constants.DRIVE_RPM_MAX / 60 * Constants.ENCODER_TICK_PER_REV * fL_bRpower);
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
        double angle = (double)Hardware.instance.gyro.getHeading();

        double dif = (wantedOmega - angle) / (currentTime - previousTime); // degrees per second

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

    @Override
    public void loop() {
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

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {
        Hardware.instance.rightBackDriveMotor.setPower(0);
        Hardware.instance.leftBackDriveMotor.setPower(0);
        Hardware.instance.rightFrontDriveMotor.setPower(0);
        Hardware.instance.leftFrontDriveMotor.setPower(0);
    }
}
