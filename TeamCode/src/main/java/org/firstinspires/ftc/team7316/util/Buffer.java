package org.firstinspires.ftc.team7316.util;

/**
 * Created by andrew on 10/11/16.
 */
public class Buffer {
    private double[] buffer;
    private int replaceIndex = 0;
    public double sum = 0;

    public Buffer(int bufferSize) {
        buffer = new double[bufferSize];
    }

    public void pushValue(double value) {
        sum -= buffer[replaceIndex];
        sum += value;
        buffer[replaceIndex]  = value;
        replaceIndex++;
        if (replaceIndex == buffer.length) {
            replaceIndex = 0;
        }
    }

    public double average() {
        return sum/buffer.length;
    }

}
