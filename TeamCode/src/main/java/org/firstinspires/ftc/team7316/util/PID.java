package org.firstinspires.ftc.team7316.util;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.copypastaLib.MotionPath;


/**
 * Generic PID class that can be assigned to specific motors.
 */

public class PID {

    public double p, i, d, f;
    private double previous, sum;

    public double out;

    private ElapsedTime timer;

    private boolean usePath;
    private MotionPath path;

    // non-motionpath garbage
    private Direction direction;
    private double previousTime;
    private int targetTicksCurrent = 0;
    private int targetTicksFinal = 0;
    private int startTicks = 0;

    public PID(double p, double i, double d, double f) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;

        out = 0;

        previousTime = 0;
        timer = new ElapsedTime();

        usePath = false;

        reset();
    }

    /**
     * Sets the PIDF constants
     */
    public void setPID(double p, double i, double d, double f) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
    }

    /**
     * Sets a motion path for the motor to follow instead of just a target distance
     */
    public void setPath(MotionPath path) {
        usePath = true;
        this.path = path;
    }

    public boolean usingPath() {
        return usePath;
    }

    /**
     * Sets a specific distance for a target, and uses a basic linear motion path
     * @param targetTicks the desired distance reach
     * @param currentPosition current position of the motor
     */
    public void setTargetTicks(int targetTicks, int currentPosition) {
        usePath = false;
        targetTicksFinal = targetTicks;
        targetTicksCurrent = currentPosition;
        startTicks = currentPosition;
        if(targetTicksCurrent < targetTicksFinal) {
            direction = Direction.FORWARD;
        }
        else {
            direction = Direction.BACKWARD;
        }
    }

    /**
     * Calculates and updates the target of the loop if there is no motion path being used
     */
    public void updateTargetTicksCurrent() {
        double elapsedTime = timer.seconds() - previousTime;
        double distance = Constants.COAST_TICKS_PER_SECOND * elapsedTime;
        switch (direction) {
            case FORWARD:
                if(targetTicksCurrent + distance > targetTicksFinal) {
                    targetTicksCurrent = targetTicksFinal;
                }
                else {
                    targetTicksCurrent += distance;
                }
                break;
            case BACKWARD:
                if(targetTicksCurrent - distance < targetTicksFinal) {
                    targetTicksCurrent = targetTicksFinal;
                }
                else {
                    targetTicksCurrent -= distance;
                }
                break;
        }
        previousTime = timer.seconds();
    }

    public int getTargetTicksCurrent() {
        if(usePath) {
            return (int)path.getPosition(this.timer.time());
        }
        return targetTicksCurrent;
    }

    public int getTargetTicksFinal() {
        if(usePath) {
            return (int)path.getTotalDistance();
        }
        return targetTicksFinal;
    }

    /**
     * Calculates the actual output to the motors
     */
    public double getPower(double error) {
        this.sum += error;

        double delta = error - this.previous;
        this.previous = error;

        out = p * error + i * sum + d * delta + f * getPredictedSpeed(timer.seconds());

        return out;
    }

    public Double getElapsedSeconds() {
        return timer.seconds();
    }

    public double getPredictedSpeed(double time) {
        if(usePath) {
            return this.path.getSpeed(time);
        }
        if(time * Constants.COAST_TICKS_PER_SECOND + startTicks > targetTicksFinal) {
            return 0;
        }
        return Constants.COAST_TICKS_PER_SECOND;
    }

    public void reset() {
        timer.reset();
        previous = 0;
        sum = 0;

        path = null;
        usePath = false;

        previousTime = 0;
        direction = Direction.STOPPED;
        targetTicksCurrent = 0;
        targetTicksFinal = 0;
    }

    /**
     * Will not be used when using a motion path
     */
    enum Direction {
        FORWARD, BACKWARD, STOPPED
    }
}
