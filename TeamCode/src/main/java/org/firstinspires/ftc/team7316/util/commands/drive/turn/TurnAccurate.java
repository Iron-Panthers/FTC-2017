package org.firstinspires.ftc.team7316.util.commands.drive.turn;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Util;
import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.sensors.GyroWrapper;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by andrew on 1/10/17.
 */
public class TurnAccurate extends Command {

    public static final double ERROR_THRESHOLD = 4;
    public static final double decreaseAmount = 0.95;
    public static final int decreaseStart = 45;
    private double minPower = 0.3; //Constants.DRIVER_MOTOR_DEADZONE + 0.05;
    public double power = 0;
    private double turnAngle;

    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private GyroWrapper gyro;
    private double target;

    /**
     *
     * @param turnAngle the amount to turn (negative for counterclockwise)
     * @param startPower the power that the turn starts at
     */
    public TurnAccurate(double turnAngle, double startPower) {
        requires(Subsystems.instance.driveBase);
        frontLeft = Hardware.instance.frontLeftDriveMotor;
        frontRight= Hardware.instance.frontRightDriveMotor;
        backLeft = Hardware.instance.backLeftDriveMotor;
        backRight = Hardware.instance.backRightDriveMotor;

        this.gyro = Hardware.instance.gyroWrapper;
        this.power = Math.abs(startPower);

        if (turnAngle < 0) {
            this.power *= -1;
            this.minPower *= -1;
        }

        this.turnAngle = turnAngle;
    }

    @Override
    public void init() {
        target = turnAngle;
    }

    @Override
    public void loop() {
        if (Math.abs(Util.wrap(target - gyro.getHeading())) < decreaseStart) {
            this.power *= decreaseAmount;
        }

        if (Math.abs(this.power) < Math.abs(this.minPower)) {
            this.power = this.minPower;
        }

        this.frontLeft.setPower(power);
        this.backLeft.setPower(power);
        this.frontRight.setPower(-power);
        this.backRight.setPower(-power);

        Hardware.log("gyro target", target);
    }

    @Override
    public boolean shouldRemove() {
        return Math.abs(Util.wrap(target - gyro.getHeading())) <= ERROR_THRESHOLD;
    }

    @Override
    public void end() {
        Subsystems.instance.driveBase.stopMotors();
    }

    /**
     * The angle distance from the targeted heading.
     * Positive angles mean to the right, while negative angles mean to the left.
     * @return the error
     */
    private double error() {
        return (gyro.getHeading() + 540 - target) % 360 - 180;
    }
}
