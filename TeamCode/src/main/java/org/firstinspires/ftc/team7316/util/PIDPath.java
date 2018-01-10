package org.firstinspires.ftc.team7316.util;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.copypastaLib.MotionPath;


/**
 * Created by andrew on 12/6/17.
 */

public class PIDPath {

    private double p, i, d, f;
    private double previous, sum;

    private double previousTime;
    private ElapsedTime timer;
    private MotionPath path;

    public PIDPath(double p, double i, double d, double f, MotionPath path) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
        this.path = path;

        previousTime = 0;
        timer = new ElapsedTime();

        reset();
    }

    public int getTargetTicksCurrent() {
        return (int)this.path.getPosition(this.timer.time());
    }

    public int getTargetTicksFinal() {
        return (int)this.path.getTotalDistance();
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
        return this.path.getSpeed(time);
    }

    public void reset() {
        timer.reset();
        previousTime = 0;
        previous = 0;
        sum = 0;
    }

}
