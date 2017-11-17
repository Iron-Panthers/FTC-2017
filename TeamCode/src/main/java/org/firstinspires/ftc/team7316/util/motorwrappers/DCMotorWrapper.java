package org.firstinspires.ftc.team7316.util.motorwrappers;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.PID;

/**
 * Created by jerry on 11/15/17.
 */

public class DCMotorWrapper {

    private DcMotor motor;
    private PID pid;

    private double maxPower;

    private int targetEncoderTicks = 0;

    public DCMotorWrapper(DcMotor motor, PID pid) {
        this.motor = motor;
        this.pid = pid;
        maxPower = 1;
    }

    public void setTargetEncoderTicks(int ticks) {
        resetEncoder();
        targetEncoderTicks = motor.getCurrentPosition() + ticks;
        pid.reset();
    }

    public void setMaxPower(double maxPower) {
        this.maxPower = maxPower;
    }

    public int getError() {
        return targetEncoderTicks - motor.getCurrentPosition();
    }

    public void setPowerPID() {
        double pow = pid.getPower(getError());
        if(Math.abs(pow) > maxPower) {
            pow = (pow > 0) ? maxPower : -maxPower;
        }
        motor.setPower(pow);
    }

    public boolean completedDistance() {
        return Math.abs(targetEncoderTicks - motor.getCurrentPosition()) < Constants.DISTANCE_ERROR_RANGE_TICKS;
    }

    public void resetEncoder() {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setPower(double power) {
        motor.setPower(power);
    }

    public void stop() {
        motor.setPower(0);
    }
}