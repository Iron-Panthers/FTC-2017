package org.firstinspires.ftc.team7316.util.commands.drive.turn;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Util;
import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.sensors.GyroWrapper;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

import java.util.ArrayList;

/**
 * Turn the robot a specific distance using PID. Stops when the correction speed is under a threshold and
 * the robot's distance from the correct angle is also under a threshold.
 */
public class TurnGyroPID extends Command {

    public static final double ERROR_THRESHOLD = 2, DELTA_THRESHOLD = 2, MAX_POWER = 1;
    private double deltaAngle, targetAngle;

    private ArrayList<Double> times = new ArrayList<>();
    private ArrayList<Double> angles = new ArrayList<>();
    private ArrayList<Double> targets = new ArrayList<>();

    private ElapsedTime timer = new ElapsedTime();
    private double timeout;

    private int completedCount;
    private final int countThreshold = 10;

    private GyroWrapper gyro;
    public double sumError, lastError, deltaError;

    /** @param deltaAngle the amount to turn */
    public TurnGyroPID(double deltaAngle) {
//        requires(Subsystems.instance.driveBase);

        Subsystems.instance.driveBase.resetMotorModes();
        gyro = Hardware.instance.gyroWrapper;
        this.deltaAngle = deltaAngle;
        targetAngle = this.deltaAngle + gyro.getHeading();
        completedCount = 0;
        timeout = 10;
    }

    public TurnGyroPID(double deltaAngle, double timeout) {
//        requires(Subsystems.instance.driveBase);

        Subsystems.instance.driveBase.resetMotorModes();
        gyro = Hardware.instance.gyroWrapper;
        this.deltaAngle = deltaAngle;
        targetAngle = this.deltaAngle + gyro.getHeading();
        completedCount = 0;
        this.timeout = timeout;
    }

    @Override
    public void init() {
        sumError = 0;
        deltaError = 0;
        lastError = error();
        timer.reset();
        Hardware.log("big man", "turning");
    }

    @Override
    public void loop() {
        double error = error();
        if(Math.abs(error) <= ERROR_THRESHOLD) {
            completedCount++;
        }
        deltaError = error - lastError;
        sumError += error;

        double power = Constants.GYRO_P *error + Constants.GYRO_I *sumError + Constants.GYRO_D *deltaError;
        Hardware.log("current error", error);
        Hardware.log("turn_power", power);

        if (Math.abs(power) > MAX_POWER) {
            power = (power > 0) ? MAX_POWER : -MAX_POWER;
        }

        Subsystems.instance.driveBase.turnMotors(power);

        lastError = error;

        times.add(timer.seconds());
        angles.add(gyro.getHeading());
        targets.add(targetAngle);
    }

    @Override
    public boolean shouldRemove() {
        return completedCount >= countThreshold || timer.seconds() > timeout;// && Math.abs(deltaError) <= DELTA_THRESHOLD;
    }

    @Override
    public void end() {
        Subsystems.instance.driveBase.stopMotors();
        Util.writeCSV(times, targets, angles);
    }

    /**
     * The angle distance from the targeted heading.
     * Positive angles mean to the right, while negative angles mean to the left.
     * @return the error
     */
    private double error() {
        return Util.wrap(targetAngle - gyro.getHeading());
    }
}
