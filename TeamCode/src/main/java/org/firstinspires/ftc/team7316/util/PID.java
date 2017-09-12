package org.firstinspires.ftc.team7316.util;

/**
 * Created by andrew on 9/12/17.
 */

public class PID {

    private float p, i, d;
    private float previous, sum;

    public PID(float p, float i, float d) {
        this.p = p;
        this.i = i;
        this.d = d;
    }

    public float newError(float error) {

        this.sum += error;

        float delta = error - this.previous;
        this.previous = error;

        float out = p * error + i * sum + d * delta;

        return out;
    }

}
