package org.firstinspires.ftc.team7316.util.sensors;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.team7316.util.Buffer;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;

/**
 * Created by jerry on 11/7/17.
 */

public class ColorWrapper {

    private Buffer redSum;
    private Buffer greenSum;
    private Buffer blueSum;

    public boolean noColor = false;

    public boolean drivenForward = true; //by default drives a shorter distance - less likely to crash?

    private ColorSensor sensor;

    public ColorWrapper(ColorSensor sensor) {
        this.sensor = sensor;
        redSum = new Buffer(Constants.COLOR_BUFFER_SIZE);
        greenSum = new Buffer(Constants.COLOR_BUFFER_SIZE);
        blueSum = new Buffer(Constants.COLOR_BUFFER_SIZE);
        reset();
    }

    public void reset() {
        redSum.clear();
        greenSum.clear();
        blueSum.clear();
    }

    public void run() {
        for(int i = 0; i < Constants.COLOR_BUFFER_SIZE; i++) {
            push();
        }
    }

    public void push() {
        redSum.pushValue(this.sensor.red() - Constants.COLOROFFSET_R);
        greenSum.pushValue(this.sensor.green() - Constants.COLOROFFSET_G);
        blueSum.pushValue(this.sensor.blue() - Constants.COLOROFFSET_B);
    }

    public void setNoColor() {
        noColor = redSum.sum <= Constants.NO_COLOR_THRESHOLD_RED && blueSum.sum <= Constants.NO_COLOR_THRESHOLD_BLUE;
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

    public void logSums() {
        Hardware.log("redsum", redSum.sum);
        Hardware.log("greensum", greenSum.sum);
        Hardware.log("bluesum", blueSum.sum);
    }

    public void logColors() {
        Hardware.log("red", Hardware.instance.colorsensor.red());
        Hardware.log("green", Hardware.instance.colorsensor.green());
        Hardware.log("blue", Hardware.instance.colorsensor.blue());
    }
}
