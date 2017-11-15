package org.firstinspires.ftc.team7316.util.sensors;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.team7316.util.Buffer;
import org.firstinspires.ftc.team7316.util.Hardware;

/**
 * Created by jerry on 11/7/17.
 */

public class ColorWrapper {

    private Buffer redSum;
    private Buffer greenSum;
    private Buffer blueSum;

    private final int bufferSize = 20;

    public boolean noColor = false;

    private ColorSensor sensor;

    public ColorWrapper(ColorSensor sensor) {
        this.sensor = sensor;
        redSum = new Buffer(bufferSize);
        greenSum = new Buffer(bufferSize);
        blueSum = new Buffer(bufferSize);
    }

    public void run() {
        for(int i = 0; i < bufferSize; i++) {
            push();
        }
    }

    public void push() {
        redSum.pushValue(this.sensor.red());
        greenSum.pushValue(this.sensor.green());
        blueSum.pushValue(this.sensor.blue());
    }

    public void setNoColor() {
        noColor = redSum.sum == greenSum.sum;
    }

    public double sumR() {
        return redSum.sum;
    }

    public double sumG() {
        return greenSum.sum;
    }

    public double sumB() {
        return blueSum.sum;
    }
}
