package org.firstinspires.ftc.team7316.util.commands.turn;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by andrew on 11/1/16.
 */
public class TurnGyro implements Loopable {

    protected DcMotor leftMotor;
    protected DcMotor rightMotor;

    private GyroSensor gyro;

    private float remainingBearing;

    protected double power;
    protected float deltaBearing;

    private final float CORRECTION_PERCENT_GYRO = 0.85f;

    /**
     *
     * @param deltaBearing never input a negative value for this
     * @param power don't turn in the inefficient direction, it will cause problems (deltaBearing < 180)
     * @param leftMotor
     * @param rightMotor
     * @param gyro
     */

    public TurnGyro(float deltaBearing, double power, DcMotor leftMotor, DcMotor rightMotor, GyroSensor gyro) { //+power = clockwise
        this.deltaBearing = deltaBearing*CORRECTION_PERCENT_GYRO;
        this.power = power;
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.gyro = gyro;
    }

    @Override
    public void init() {
        this.gyro.resetZAxisIntegrator();
    }


    @Override
    public void loop() {
        this.leftMotor.setPower(this.power);
        this.rightMotor.setPower(-this.power);
    }


    @Override
    public boolean shouldRemove() {
        if (this.power > 0) { // if turning clockwise
           return this.gyro.getHeading() >= this.deltaBearing;
        } else { // if turning ccw
            if (gyro.getHeading() < 180) {
                return false;
            }
            return this.gyro.getHeading() <= (360 - this.deltaBearing);
        }
    }

    @Override
    public void terminate() {
        this.leftMotor.setPower(0);
        this.rightMotor.setPower(0);
    }

}
