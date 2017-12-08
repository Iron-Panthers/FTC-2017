package org.firstinspires.ftc.team7316.util.motorwrappers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.PID;
import org.firstinspires.ftc.team7316.util.PIDPath;
import org.firstinspires.ftc.team7316.util.path.MotionPath;

/**
 * Created by jerry on 11/15/17.
 */

public class DCMotorWrapper {

    private DcMotor motor;
    public PID pid;

    private double maxPower;

    private ElapsedTime timer;
    private double previoustime;
    private int previoustick;

    private PIDPath pidPath;
    private boolean followPath;

    public DCMotorWrapper(DcMotor motor, PID pid) {
        this.motor = motor;
        this.pid = pid;
        maxPower = 1;

        previoustick = 0;
        previoustime = 0;
        timer = new ElapsedTime();
    }

    public void resetTimer() {
        timer.reset();
    }

    public void setTargetInches(double inches) {
        pid.setTargetTicks(motor.getCurrentPosition() + (int)Constants.inchesToTicks(inches), motor.getCurrentPosition());
    }

    public void setPath(MotionPath path) {
        this.followPath = true;
        this.pidPath = new PIDPath(pid.p, pid.i, pid.d, pid.f, path);
    }

    public void setTargetTicks(int ticks) {
        pid.setTargetTicks(motor.getCurrentPosition() + ticks, motor.getCurrentPosition());
        this.followPath = false;
    }

    public void setMaxPower(double maxPower) {
        this.maxPower = maxPower;
    }

    public int getError() {
        return pid.getTargetTicksCurrent() - motor.getCurrentPosition();
    }

    public void setPowerPID() {

        previoustime = timer.seconds();
        previoustick = motor.getCurrentPosition();
        double pow = 0;

        if (followPath) {
            pow = pidPath.getPower(getError());
        } else
            {
            pid.updateTargetTicksCurrent();
            pow = pid.getPower(getError());
        }

        if (Math.abs(pow) > maxPower) {
            pow = (pow > 0) ? maxPower : -maxPower;
        }
        motor.setPower(pow);
    }

    public boolean completedDistance() {
        return Math.abs(pid.getTargetTicksFinal() - motor.getCurrentPosition()) < Constants.DISTANCE_ERROR_RANGE_TICKS;
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

    public void reset() {
        stop();
        resetEncoder();
        maxPower = 1;

        pid.reset();
        if (this.pidPath != null) {
            this.pidPath.reset();
        }
    }

}
