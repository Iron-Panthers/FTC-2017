package org.firstinspires.ftc.team7316.util;

/**
 * Created by andrew on 9/12/17.
 */

public class PID {

    private double p, i, d;
    private double previous, sum;

    public PID(double p, double i, double d) {
        this.p = p;
        this.i = i;
        this.d = d;
    }

    public double newError(double error, double deltaT) {

        this.sum += error;

        double delta = (error - this.previous) / deltaT;
        this.previous = error;

        double out = p * error + i * sum + d * delta;

        return out;
    }

}
