package org.firstinspires.ftc.team7316.util.commands.turn;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by andrew on 1/10/17.
 */
public class TurnAccurate implements Loopable {

    public static final double ERROR_THRESHOLD = 4;
    public static final double decreaseAmount = 0.85;
    public static final int decreaseStart = 45;
    private double minPower = 0.05; //Constants.DRIVER_MOTOR_DEADZONE + 0.05;
    public double power = 0;
    private int turnAngle;

    private DcMotor left, right;
    private GyroSensor gyro;
    private int target;
    public double sumError, lastError, deltaError;

    /**
     *
     * @param left
     * @param right
     * @param gyro
     * @param turnAngle the amount to turn (negative for counterclockwise)
     * @param startPower the power that the turn starts at
     */
    public TurnAccurate(DcMotor left, DcMotor right, GyroSensor gyro, int turnAngle, double startPower) {
        this.left = left;
        this.right = right;
        this.gyro = gyro;
        this.power = Math.abs(startPower);

        if (turnAngle < 0) {
            this.power *= -1;
            this.minPower *= -1;
        }

        this.turnAngle = turnAngle;
    }

    @Override
    public void init() {
        gyro.resetZAxisIntegrator();
        target = turnAngle;
    }

    @Override
    public void loop() {
        if (Math.abs(error()) < decreaseStart) {
            this.power *= this.decreaseAmount;
        }

        if (Math.abs(this.power) < Math.abs(this.minPower)) {
            this.power = this.minPower;
        }

        this.left.setPower(power);
        this.right.setPower(-power);
    }

    @Override
    public boolean shouldRemove() {
        return Math.abs(error()) <= ERROR_THRESHOLD;
    }

    @Override
    public void terminate() {
        left.setPower(0);
        right.setPower(0);
    }

    /**
     * The angle distance from the targeted heading.
     * Positive angles mean to the right, while negative angles mean to the left.
     * @return the error
     */
    private int error() {
        return (gyro.getHeading() + 540 - target) % 360 - 180;
    }
}
