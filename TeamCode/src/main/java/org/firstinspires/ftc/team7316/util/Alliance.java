package org.firstinspires.ftc.team7316.util;

import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by Maxim on 10/18/2016.
 */
public enum Alliance {
    RED, BLUE;

    /**
     * Check if good good
     * @param bluesum aggregate blue values from color sensor
     * @param redsum aggregate red values from color sensor
     * @return is good good or not is good good
     */

    // for jewel auto hitting jewel by driving
    public boolean shouldHitForward(double redsum, double bluesum) {
        switch (this) {
            case BLUE:
                return bluesum - redsum >= Constants.COLOR_DIFFERENCE;
            case RED:
                return redsum - bluesum >= Constants.COLOR_DIFFERENCE;
        }
        throw new IllegalArgumentException("Something wrong happened in enum Alliance even though nothing wrong should have happened");
    }


}
