package org.firstinspires.ftc.team7316.util;

import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by Maxim on 10/18/2016.
 */
public enum Alliance {
    RED, BLUE;

    /**
     * Check if good good
     * @param sensor the sensor to check if good good
     * @return is good good or not is good good
     */
    public boolean shouldPressLeftServo(ColorSensor sensor) {
        switch (this) {
            case RED:
                return sensor.red() - sensor.blue() >= Constants.COLOR_DIFFERENCE;
            case BLUE:
                return sensor.blue() - sensor.red() >= Constants.COLOR_DIFFERENCE;
        }
        throw new IllegalArgumentException("Something wrong happened in enum Alliance even though nothing wrong should have happened");
    }
}
