package org.firstinspires.ftc.team7316.util;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.path.MotionPath;

/**
 * Created by andrew on 9/12/17.
 */

public class PID {

    public double p, i, d, f;
    private double previous, sum;

    private ElapsedTime timer;

    private boolean usePath;
    private MotionPath path;

    /** non-motionpath garbage */
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

        previousTime = 0;
        timer = new ElapsedTime();

        usePath = false;

        reset();
    }

    public void setPath(MotionPath path) {
        usePath = true;
        this.path = path;
    }

    public boolean usingPath() {
        return usePath;
    }

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

    public double getPower(double error) {
        this.sum += error;

        double delta = error - this.previous;
        this.previous = error;

        double out = p * error + i * sum + d * delta + f * getPredictedSpeed(timer.seconds());

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

        previousTime = 0;
        direction = Direction.STOPPED;
        targetTicksCurrent = 0;
        targetTicksFinal = 0;
    }

    /**
     * won't be needed for path
     */
    enum Direction {
        FORWARD, BACKWARD, STOPPED
    }
}
