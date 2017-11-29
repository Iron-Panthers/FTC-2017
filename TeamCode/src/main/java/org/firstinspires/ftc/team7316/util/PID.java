package org.firstinspires.ftc.team7316.util;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by andrew on 9/12/17.
 */

public class PID {

    private double p, i, d, f;
    private double previous, sum;
    private Direction direction;

    private double previousTime;
    private ElapsedTime timer;

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

        reset();
    }

    public void setTargetTicks(int targetTicks, int currentPosition) {
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
        return targetTicksCurrent;
    }

    public int getTargetTicksFinal() {
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
        if(time * Constants.COAST_TICKS_PER_SECOND + startTicks > targetTicksFinal) {
            return 0;
        }
        return Constants.COAST_TICKS_PER_SECOND;
    }

    public void reset() {
        timer.reset();
        previousTime = 0;
        direction = Direction.STOPPED;
        targetTicksCurrent = 0;
        targetTicksFinal = 0;
        previous = 0;
        sum = 0;
    }

    enum Direction {
        FORWARD, BACKWARD, STOPPED
    }
}
