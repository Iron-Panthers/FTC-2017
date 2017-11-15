package org.firstinspires.ftc.team7316.util.commands.turn;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Util;
import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.sensors.GyroWrapper;
import org.firstinspires.ftc.team7316.util.subsystems.MecanumDriveBase;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Turn the robot a specific distance using PID. Stops when the correction speed is under a threshold and
 * the robot's distance from the correct angle is also under a threshold.
 */
public class TurnGyroPID extends Command {

    public static final double P = 0.023f, I = 0, D = 0;
    public static final double ERROR_THRESHOLD = 5, DELTA_THRESHOLD = 0;
    private static final double maxPower = 0.4;
    private static final double minPower = 0.08;
    public double power = 0;
    private double turnAngle;

    private GyroWrapper gyro;
    private ElapsedTime timer;
    private double target;
    public double sumError, lastError, deltaError;

    /** @param turnAngle the amount to turn */
    public TurnGyroPID(double turnAngle) {
        requires(Subsystems.instance.driveBase);

        Subsystems.instance.driveBase.resetMotorModes();
        gyro = new GyroWrapper(Hardware.instance.gyro);
        this.turnAngle = turnAngle;
        this.timer = new ElapsedTime();
    }

    @Override
    public void init() {
        sumError = 0;
        deltaError = 0;
        timer.reset();
        target = turnAngle;
        lastError = error();
    }

    @Override
    public void loop() {
        //double deltaTime = timer.time();
        double error = error();
        //deltaError = (error - lastError) / deltaTime;
        //sumError += deltaError;

        //power = P*error + I*sumError + D*deltaError;
        power = P*error;

        if (Math.abs(power) > maxPower) {
            power = (power > 0 ? maxPower : -maxPower);
        }
        else if (Math.abs(power) < minPower) {
            power = (power > 0 ? minPower : -minPower);
        }

        Subsystems.instance.driveBase.turnMotors(power);

        lastError = error;
        timer.reset();

        Hardware.log(Hardware.tag, power);
        Hardware.log("gyro target", target);
    }

    @Override
    public boolean shouldRemove() {
        //return Math.abs(Util.wrap(target - gyro.getHeading())) <= ERROR_THRESHOLD && Math.abs(deltaError) <= DELTA_THRESHOLD;
        return Math.abs(error()) <= ERROR_THRESHOLD;
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
        return Util.wrap(gyro.getHeading() - target);
    }
}
