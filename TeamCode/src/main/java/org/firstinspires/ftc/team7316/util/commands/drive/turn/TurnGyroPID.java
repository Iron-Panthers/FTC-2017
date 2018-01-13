package org.firstinspires.ftc.team7316.util.commands.drive.turn;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Util;
import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.sensors.GyroWrapper;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Turn the robot a specific distance using PID. Stops when the correction speed is under a threshold and
 * the robot's distance from the correct angle is also under a threshold.
 */
public class TurnGyroPID extends Command {

    public static final double ERROR_THRESHOLD = 1, DELTA_THRESHOLD = 2, MAX_POWER = 1, ACCEL_RATE = 240;
    private double deltaAngle, startAngle, targetAngleCurrent, targetAngleFinal, TURN_TIME, ACCEL_TIME, COAST_TIME, MAX_SPEED;

    private Direction direction;

    private ArrayList<Double> times = new ArrayList<>();
    private ArrayList<Double> angles = new ArrayList<>();
    private ArrayList<Double> currenttargets = new ArrayList<>();
    private ArrayList<Double> finaltargets = new ArrayList<>();

    private ElapsedTime timer = new ElapsedTime();
    private double previousTime = 0;
    private double timeout;

    private int completedCount = 0;
    private final int countThreshold = 10;

    private GyroWrapper gyro = Hardware.instance.gyroWrapper;;
    public double sumError, lastError, deltaError;

    /** @param deltaAngle the amount to turn in DEGREES */
    public TurnGyroPID(double deltaAngle) {
        this(deltaAngle, 6);
    }

    public TurnGyroPID(double deltaAngle, double timeout) {
        this(deltaAngle, timeout, 90);
    }

    public TurnGyroPID(double deltaAngle, double timeout, double maxspeed) {
        this.MAX_SPEED = maxspeed;

        this.deltaAngle = deltaAngle;
        this.timeout = timeout;

        this.ACCEL_TIME = this.MAX_SPEED / ACCEL_RATE;
        this.COAST_TIME = (deltaAngle - ACCEL_RATE * ACCEL_TIME * ACCEL_TIME) / this.MAX_SPEED;
        this.TURN_TIME = ACCEL_TIME * 2 + COAST_TIME;
    }

    @Override
    public void init() {
        startAngle = gyro.getHeading();
        targetAngleCurrent = gyro.getHeading();
//        targetAngleFinal = this.deltaAngle + gyro.getHeading();
        targetAngleFinal = this.deltaAngle; //TEMPORARY
        completedCount = 0;

        System.out.println("start angle: " + startAngle);
        System.out.println("final angle: " + targetAngleFinal);

        if(targetAngleCurrent < targetAngleFinal) {
            direction = Direction.RIGHT;
        }
        else {
            direction = Direction.LEFT;
        }

        Subsystems.instance.driveBase.resetMotorModes();
        sumError = 0;
        deltaError = 0;
        lastError = error();
        timer.reset();
        Hardware.log("big man", "turning");
    }

    @Override
    public void loop() {
        updateCurrentTarget();

        if(Math.abs(deltaAngle - gyro.getHeading()) <= ERROR_THRESHOLD) {
            completedCount++;
        }

        double error = error();
        deltaError = error - lastError;
        sumError += error;

        double power = Constants.GYRO_P *error + Constants.GYRO_I *sumError + Constants.GYRO_D*deltaError + Constants.GYRO_F*getPredictedSpeed(timer.seconds());
        Hardware.log("current error", error);
        Hardware.log("current target", targetAngleCurrent);
        Hardware.log("delta error", deltaError);
        Hardware.log("turn_power", power);
        Hardware.log("final target", targetAngleFinal);

        if (Math.abs(power) > MAX_POWER) {
            power = (power > 0) ? MAX_POWER : -MAX_POWER;
        }

        Subsystems.instance.driveBase.turnMotors(power);

        lastError = error;

        System.out.println("current target: " + targetAngleCurrent);
        System.out.println("current heading: " + gyro.getHeading());

        times.add(timer.seconds());
        angles.add(gyro.getHeading());
        currenttargets.add(targetAngleCurrent);
        finaltargets.add(targetAngleFinal);
    }

    @Override
    public boolean shouldRemove() {
        return completedCount >= countThreshold || timer.seconds() > timeout;
    }

    @Override
    public void end() {
        Subsystems.instance.driveBase.stopMotors();
        writeCSVGyro(times, currenttargets, angles);
    }

    private void updateCurrentTarget() {
        double time = timer.seconds();
        switch (direction) {
            case RIGHT:
                if (time < ACCEL_TIME) {
                    targetAngleCurrent = 0.5 * ACCEL_RATE * time * time;
                } else if (time < COAST_TIME + ACCEL_TIME) {
                    time -= ACCEL_TIME;
                    targetAngleCurrent = 0.5 * ACCEL_RATE * ACCEL_TIME * ACCEL_TIME + time * this.MAX_SPEED;
                } else if (time < TURN_TIME) {
                    double currentSpeed = getPredictedSpeed(time);
                    time -= ACCEL_TIME + COAST_TIME;
                    targetAngleCurrent = 0.5 * ACCEL_RATE * ACCEL_TIME * ACCEL_TIME + (COAST_TIME) * this.MAX_SPEED + 0.5 * time * (currentSpeed + this.MAX_SPEED);
                } else {
                    targetAngleCurrent = targetAngleFinal;
                }
                break;
            case LEFT:
                if (time < ACCEL_TIME) {
                    targetAngleCurrent = 0.5 * ACCEL_RATE * time * time;
                } else if (time < COAST_TIME + ACCEL_TIME) {
                    time -= ACCEL_TIME;
                    targetAngleCurrent = 0.5 * ACCEL_RATE * ACCEL_TIME * ACCEL_TIME + time * this.MAX_SPEED;
                } else if (time < TURN_TIME) {
                    double currentSpeed = -getPredictedSpeed(time);
                    time -= ACCEL_TIME + COAST_TIME;
                    targetAngleCurrent = 0.5 * ACCEL_RATE * ACCEL_TIME * ACCEL_TIME + (COAST_TIME) * this.MAX_SPEED + 0.5 * time * (currentSpeed + this.MAX_SPEED);
                } else {
                    targetAngleCurrent = -targetAngleFinal;
                }
                targetAngleCurrent *= -1;
                break;
        }
    }

    /*private void updateCurrentTarget() {
        double elapsedTime = timer.seconds() - previousTime;
        double distance = this.MAX_SPEED * elapsedTime;
        switch (direction) {
            case RIGHT:
                if(targetAngleCurrent + distance > targetAngleFinal) {
                    targetAngleCurrent = targetAngleFinal;
                }
                else {
                    targetAngleCurrent += distance;
                }
                break;
            case LEFT:
                if(targetAngleCurrent - distance < targetAngleFinal) {
                    targetAngleCurrent = targetAngleFinal;
                }
                else {
                    targetAngleCurrent -= distance;
                }
                break;
        }
        previousTime = timer.seconds();
    }*/

    private double getPredictedSpeed(double time) {
        double speed = 0;

        if (time < ACCEL_TIME) {
            speed = ACCEL_RATE * time;
        } else if (time < ACCEL_TIME + COAST_TIME) {
            speed = this.MAX_SPEED;
        } else if (time < TURN_TIME) {
            time -= ACCEL_TIME + COAST_TIME;
            speed = this.MAX_SPEED - ACCEL_RATE * time;
        }

        if (direction == Direction.LEFT) {
            speed *= -1;
        }

        Hardware.log("speed", speed);

        return speed;
    }

    /**
     * The angle distance from the targeted heading.
     * Positive angles mean to the right, while negative angles mean to the left.
     * @return the error
     */
    private double error() {
        return Util.wrap(targetAngleCurrent - gyro.getHeading());
    }

    public static void writeCSVGyro(List<Double> times, List<Double> targets, List<Double> positions) {
        BufferedWriter os;
        Date date = new Date(System.currentTimeMillis());
        String timestamp = new SimpleDateFormat("yyyyMMdd-HH:mm:ss").format(date);
        try {
            File dir = new File("/storage/emulated/0/gyropidoutput-" + timestamp + ".csv");
            os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dir)));
            os.write("times,target,positions,p,i,d,f,velocityPrediction,tolerance\n");
            os.write(String.format("%s,%s,%s,%s,%s,%s,%s\n", times.get(0), targets.get(0), positions.get(0),
                    Constants.GYRO_P, Constants.GYRO_I, Constants.GYRO_D, Constants.GYRO_F));
            for (int i=1; i < times.size(); i++) {
                os.write(String.format("%s,%s,%s\n", times.get(i), targets.get(i), positions.get(i)));
            }
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    enum Direction {
        RIGHT, LEFT, STOPPED
    }
}
