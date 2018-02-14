package org.firstinspires.ftc.team7316.util.commands.drive.turn;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.copypastaLib.CombinedPath;
import org.firstinspires.ftc.team7316.copypastaLib.MotionPath;
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

    public static final double ERROR_THRESHOLD = 2, DELTA_THRESHOLD = 2, MAX_POWER = 1, ACCEL_RATE = 150;
    private double deltaAngle, startAngle, targetAngleCurrent, targetAngleFinal, TURN_TIME, ACCEL_TIME, COAST_TIME, MAX_SPEED;

    private Direction direction;

    private ArrayList<Double> times = new ArrayList<>();
    private ArrayList<Double> angles = new ArrayList<>();
    private ArrayList<Double> currenttargets = new ArrayList<>();
    private ArrayList<Double> finaltargets = new ArrayList<>();
    private ArrayList<Double> errors = new ArrayList<>();
    private ArrayList<Double> powers = new ArrayList<>();

    private ArrayList<Double> p_vals = new ArrayList<>();
    private ArrayList<Double> i_vals = new ArrayList<>();
    private ArrayList<Double> d_vals = new ArrayList<>();
    private ArrayList<Double> f_vals = new ArrayList<>();

    private ElapsedTime timer = new ElapsedTime();
    private double previousTime = 0;
    private double timeout;

    private int completedCount = 0;
    private final int countThreshold = 10;

    public MotionPath path1;

    private GyroWrapper gyro = Hardware.instance.gyroWrapper;
    public double sumError, lastError, deltaError;

    /**
     * DISCLAIMER: right now it's not delta angle, but the actual target angle
     * this will be fixed later
     * and by later i mean never
     * @param deltaAngle the amount to turn in DEGREES
     */
    public TurnGyroPID(double deltaAngle) {
        this(deltaAngle, 6);
    }

    public TurnGyroPID(double deltaAngle, double timeout) {
        this(deltaAngle, timeout, 90);
    }

    public TurnGyroPID(double deltaAngle, double timeout, double maxspeed) {
        requires(Subsystems.instance.driveBase);
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
            path1 = new CombinedPath.LongitudalTrapezoid(startAngle, targetAngleFinal - startAngle, MAX_SPEED, ACCEL_RATE);
        }
        else {
            direction = Direction.LEFT;
            path1 = new CombinedPath.LongitudalTrapezoid(startAngle, targetAngleFinal - startAngle, -MAX_SPEED, -ACCEL_RATE);
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
        double time = timer.seconds();
        updateCurrentTarget(time);

        if(Math.abs(deltaAngle - gyro.getHeading()) <= ERROR_THRESHOLD) {
            completedCount++;
        }

        double error = error();
        double deltaTime = time - previousTime;
        deltaError = (error - lastError) * deltaTime;
        sumError += error;

        double power = Constants.GYRO_P *error + Constants.GYRO_I *sumError + Constants.GYRO_D*deltaError + Constants.GYRO_F*getPredictedSpeed(time);

        Hardware.log("current error", error);
        Hardware.log("current target", targetAngleCurrent);
        Hardware.log("delta error", deltaError);
        Hardware.log("turn_power", power);
        Hardware.log("final target", targetAngleFinal);

        if (Math.abs(power) > MAX_POWER) {
            power = (power > 0) ? MAX_POWER : -MAX_POWER;
        }

        Subsystems.instance.driveBase.turnMotors(Util.deadzoneChange(power));

        System.out.println("current target: " + targetAngleCurrent);
        System.out.println("current heading: " + gyro.getHeading());

        times.add(timer.seconds());
        angles.add(gyro.getHeading());
        currenttargets.add(targetAngleCurrent);
        finaltargets.add(targetAngleFinal);
        errors.add(error);
        powers.add(power);

        p_vals.add(Constants.GYRO_P *error);
        i_vals.add(Constants.GYRO_I *sumError);
        d_vals.add(Constants.GYRO_D*deltaError);
        f_vals.add(Constants.GYRO_F*getPredictedSpeed(time));

        lastError = error;
        previousTime = time;
    }

    @Override
    public boolean shouldRemove() {
        return completedCount >= countThreshold || timer.seconds() > timeout;
    }

    @Override
    public void end() {
        Subsystems.instance.driveBase.stopMotors();
        writeCSVGyro(times, currenttargets, angles, errors, powers, p_vals, i_vals, d_vals, f_vals);
    }

    private void updateCurrentTarget(double time) {
//        switch (direction) {
//            case RIGHT:
//                if (time < ACCEL_TIME) {
//                    targetAngleCurrent = 0.5 * ACCEL_RATE * time * time;
//                } else if (time < COAST_TIME + ACCEL_TIME) {
//                    time -= ACCEL_TIME;
//                    targetAngleCurrent = 0.5 * ACCEL_RATE * ACCEL_TIME * ACCEL_TIME + time * this.MAX_SPEED;
//                } else if (time < TURN_TIME) {
//                    double currentSpeed = getPredictedSpeed(time);
//                    time -= ACCEL_TIME + COAST_TIME;
//                    targetAngleCurrent = 0.5 * ACCEL_RATE * ACCEL_TIME * ACCEL_TIME + (COAST_TIME) * this.MAX_SPEED + 0.5 * time * (currentSpeed + this.MAX_SPEED);
//                } else {
//                    targetAngleCurrent = targetAngleFinal;
//                }
//                break;
//            case LEFT:
//
//                if (time < ACCEL_TIME) {
//                    targetAngleCurrent = 0.5 * ACCEL_RATE * time * time;
//                } else if (time < COAST_TIME + ACCEL_TIME) {
//                    time -= ACCEL_TIME;
//                    targetAngleCurrent = 0.5 * ACCEL_RATE * ACCEL_TIME * ACCEL_TIME + time * this.MAX_SPEED;
//                } else if (time < TURN_TIME) {
//                    double currentSpeed = -getPredictedSpeed(time);
//                    time -= ACCEL_TIME + COAST_TIME;
//                    targetAngleCurrent = 0.5 * ACCEL_RATE * ACCEL_TIME * ACCEL_TIME + (COAST_TIME) * this.MAX_SPEED + 0.5 * time * (currentSpeed + this.MAX_SPEED);
//                } else {
//                    targetAngleCurrent = -targetAngleFinal;
//                }
//                targetAngleCurrent *= -1;
//                break;
//        }
        targetAngleCurrent = path1.getPosition(time);
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
//        double speed = 0;
//
//        if (time < ACCEL_TIME) {
//            speed = ACCEL_RATE * time;
//        } else if (time < ACCEL_TIME + COAST_TIME) {
//            speed = this.MAX_SPEED;
//        } else if (time < TURN_TIME) {
//            time -= ACCEL_TIME + COAST_TIME;
//            speed = this.MAX_SPEED - ACCEL_RATE * time;
//        }
//
//        if (direction == Direction.LEFT) {
//            speed *= -1;
//        }
//
//        Hardware.log("speed", speed);

        return path1.getSpeed(time);
    }

    /**
     * The angle distance from the targeted heading.
     * Positive angles mean to the right, while negative angles mean to the left.
     * @return the error
     */
    private double error() {
        return Util.wrap(targetAngleCurrent - gyro.getHeading());
    }

    public static void writeCSVGyro(List<Double> times, List<Double> targets, List<Double> positions, List<Double> errors, List<Double> powers,
                                    List<Double> p_vals, List<Double> i_vals, List<Double> d_vals, List<Double> f_vals) {
        BufferedWriter os;
        Date date = new Date(System.currentTimeMillis());
        String timestamp = new SimpleDateFormat("yyyyMMdd-HH:mm:ss").format(date);
        try {
            File dir = new File("/storage/emulated/0/gyropidoutput-" + timestamp + ".csv");
            os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dir)));
            os.write("times,target,positions,errors,powers,p_vals,i_vals,d_vals,f_vals,p,i,d,f\n");
            os.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n", times.get(0), targets.get(0), positions.get(0), errors.get(0), powers.get(0),
                    p_vals.get(0), i_vals.get(0), d_vals.get(0), f_vals.get(0),
                    Constants.GYRO_P, Constants.GYRO_I, Constants.GYRO_D, Constants.GYRO_F));
            for (int i=1; i < times.size(); i++) {
                os.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s\n", times.get(i), targets.get(i), positions.get(i), errors.get(i), powers.get(i),
                        p_vals.get(i), i_vals.get(i), d_vals.get(i), f_vals.get(i)));
            }
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printPIDVal(double error, double sumError, double deltaError) {
        System.out.println("P-value: " + Constants.GYRO_P * error);
        System.out.println("I-value: " + Constants.GYRO_I * sumError);
        System.out.println("D-value: " + Constants.GYRO_D * deltaError);
        System.out.println("sum: " + (Constants.GYRO_P *error + Constants.GYRO_I *sumError + Constants.GYRO_D*deltaError));
    }

    enum Direction {
        RIGHT, LEFT, STOPPED
    }
}
