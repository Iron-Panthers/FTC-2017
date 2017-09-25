package org.firstinspires.ftc.team7316.util.subsystems;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.PID;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 9/12/17.
 */

public class MechanumDriveBase implements Loopable {

    private PID fR_bL_PID; //front right and back left PID engine
    private PID fL_bR_PID; //front left and back right PID engine;

    private PID gyroPID;

    private float wantedAngle, wantedMovementAngle; //in degrees
    private float wantedSpeed; //in some sort of units idk lol

    private float weighting = 0.7f;

    @Override
    public void init() {
        this.fR_bL_PID = new PID(Constants.encoderP, Constants.encoderI, Constants.encoderD);
        this.fL_bR_PID = new PID(Constants.encoderP, Constants.encoderI, Constants.encoderD);
        this.gyroPID = new PID(Constants.gyroP, Constants.gyroI, Constants.gyroD);
    }


    //setters
    public void setWantedSpeed(float speed) {
        this.wantedSpeed = speed;
    }

    public void setWantedAngle(float wantedAngle) {
        this.wantedAngle = wantedAngle;
    }

    public void setWantedMovementAngle(float wantedMovementAngle) {
        this.wantedMovementAngle = wantedMovementAngle;
    }


    //errors
    private float fR_bL_Error() {
        return 0;
    }

    private float fL_bR_Error() {
        return 0;
    }

    private float gyroError() {
        float angle = (float)Hardware.instance.gyro.getHeading();

        float dif = angle - wantedAngle;

        return dif;
    }



    private float pidToMotorPower(float out) {
        float absOut = Math.abs(out);
        float mult = (absOut/out); //1 if positive -1 if negative

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
        float fR_bL_Pwr = pidToMotorPower(this.fR_bL_PID.newError(fR_bL_Error()));
        float fL_bR_Pwr = pidToMotorPower(this.fL_bR_PID.newError(fL_bR_Error()));

        float gyro_Pwr = pidToMotorPower(this.gyroPID.newError(gyroError()));

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
