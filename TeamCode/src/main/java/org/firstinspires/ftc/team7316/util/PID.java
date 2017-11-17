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

        reset();
    }

    public double getPower(double error) {
        this.sum += error;

        double delta = error - this.previous;
        this.previous = error;

        double out = p * error + i * sum + d * delta;

        return out;
    }

    public void reset() {
        previous = 0;
        sum = 0;
    }
}
