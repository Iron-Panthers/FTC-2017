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

public class MecanumDriveBase implements Loopable {

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

    private double weighting = 0.75;

    @Override
    public void init() {


        //this.wantedFlBrSpeed = fL_bR_Error();
        //this.wantedFrBlSpeed = fR_bL_Error();

        this.previousTime = System.currentTimeMillis() / 1000;
    }

    //setters
    public void setWantedOmega(double wantedOmega) {
        // something something wanted speed
        this.wantedOmega = wantedOmega; // degrees per second
    }

    //something something no pid here
    public void setWantedTurnSpeed(double wantedTurnSpeed) {
        this.wantedTurnSpeed = wantedTurnSpeed;
    }

    public void setWantedSpeedAndMovementAngle(double wantedSpeed, double wantedMovementAngle) {
        // this  T I D B I T  is for strafing
        double y = wantedSpeed * Math.cos(wantedMovementAngle);
        double x = wantedSpeed * Math.sin(wantedMovementAngle);

        double fL_bRpower = Constants.sqrt2 * (y + (x-y)/2); //length of da vector
        double fR_bLpower = -Constants.sqrt2 * ((x-y)/2); //again

        // ticks per second
        //wantedFrBlSpeed = (double)(Constants.DRIVE_RPM_MAX / 60 * Constants.ENCODER_TICK_PER_REV * fR_bLpower);
        //wantedFlBrSpeed = (double)(Constants.DRIVE_RPM_MAX / 60 * Constants.ENCODER_TICK_PER_REV * fL_bRpower);
        wantedFrBlSpeed = fR_bLpower;
        wantedFlBrSpeed = fL_bRpower;
    }

    @Override
    public void loop() {
        Hardware.instance.rightBackDriveMotor.setPower(weighting * (wantedFlBrSpeed - wantedTurnSpeed));
        Hardware.instance.leftFrontDriveMotor.setPower(weighting * (wantedFlBrSpeed + wantedTurnSpeed));
        Hardware.instance.rightFrontDriveMotor.setPower(weighting * (wantedFrBlSpeed - wantedTurnSpeed));
        Hardware.instance.leftBackDriveMotor.setPower(weighting * (wantedFrBlSpeed + wantedTurnSpeed));
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
