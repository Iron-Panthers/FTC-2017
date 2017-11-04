package org.firstinspires.ftc.team7316.util.commands;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.team7316.util.Buffer;
import org.firstinspires.ftc.team7316.util.subsystems.JewelArm;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 10/26/17.
 */

public class AggregrateColorSensor extends Command {

    private Buffer redSum;
    private Buffer greenSum;
    private Buffer blueSum;

    private final static int bufferSize = 60;

    private ColorSensor sensor;

    public AggregrateColorSensor(ColorSensor sensor) {
        this.sensor = sensor;
        requires(Subsystems.instance.jewelArm);
    }
    
    @Override
    public void init() {
        redSum = new Buffer(bufferSize);
        greenSum = new Buffer(bufferSize);
        blueSum = new Buffer(bufferSize);
    }

    @Override
    public void loop() {
        redSum.pushValue(this.sensor.red());
        greenSum.pushValue(this.sensor.green());
        blueSum.pushValue(this.sensor.blue());
    }

    @Override
    public boolean shouldRemove() {
        return blueSum.getIndex() == bufferSize;
    }

    @Override
    protected void end() {

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
