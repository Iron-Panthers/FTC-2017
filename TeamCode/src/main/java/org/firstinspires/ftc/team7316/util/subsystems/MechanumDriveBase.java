package org.firstinspires.ftc.team7316.util.subsystems;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.PID;
import org.firstinspires.ftc.team7316.util.Util;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;
import org.firstinspires.ftc.team7316.util.subsystems.mecanum.MecanumVectorSet;

/**
 * Created by andrew on 9/12/17.
 */

public class MechanumDriveBase implements Loopable {

    private PID fR_bL_PID; //front right and back left PID engine
    private float wantedFrBlTicks = 0;

    private PID fL_bR_PID; //front left and back right PID engine;
    private float wantedFlBrTicks = 0;

    private PID gyroPID;
    private float wantedAngle = 0;

    private float wantedOmega, wantedMovementOmega; //in degrees
    private float wantedSpeed; //in some sort of units idk lol

    private float weighting = 0.7f;

    @Override
    public void init() {
        this.fR_bL_PID = new PID(Constants.encoderP, Constants.encoderI, Constants.encoderD);
        this.fL_bR_PID = new PID(Constants.encoderP, Constants.encoderI, Constants.encoderD);
        this.gyroPID = new PID(Constants.gyroP, Constants.gyroI, Constants.gyroD);

        this.wantedFlBrTicks = fL_bR_Error();
        this.wantedFrBlTicks = fR_bL_Error();
    }

    //setters
    public void setWantedSpeed(float speed) {
        this.wantedSpeed = speed;
    }

    public void setWantedAngle(float wantedOmega) {
        this.wantedOmega = wantedOmega;
    }

    public void setWantedMovementAngle(float wantedMovementOmega) {
        this.wantedMovementOmega = wantedMovementOmega;
    }


    //errors
    private float fR_bL_Error() {
        int fRError = Hardware.instance.rightFrontDriveMotor.getCurrentPosition();
        int bLError = Hardware.instance.leftBackDriveMotor.getCurrentPosition();

        float average = (fRError + bLError) / 2.0f;
        return average - this.wantedFrBlTicks;
    }

    private float fL_bR_Error() {
        int fLError = Hardware.instance.leftFrontDriveMotor.getCurrentPosition();
        int bRError = Hardware.instance.rightBackDriveMotor.getCurrentPosition();

        float average = (fLError + bRError) / 2.0f;
        return average - this.wantedFlBrTicks;
    }

    private float gyroError() {
        float angle = (float)Hardware.instance.gyro.getHeading();

        float dif = angle - wantedAngle;

        return Util.wrap(dif);
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

    public void updateWantedFromJoystick() {

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
