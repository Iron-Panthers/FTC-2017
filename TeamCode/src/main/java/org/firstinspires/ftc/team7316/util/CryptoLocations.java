package org.firstinspires.ftc.team7316.util;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by andrew on 2/22/18.
 */

public class CryptoLocations {

    public static final double RED_TURN_TO_PICTO = 18;
    public static final double BLUE_TURN_TO_PITCO = 25;

    public static final int CLOSE_RED_AUTO = 0;
    public static final int FAR_RED_AUTO = 1;
    public static final int CLOSE_BLUE_AUTO = 2;
    public static final int FAR_BLUE_AUTO = 3;

    public static RelicRecoveryVuMark PICTO_LOCATION;
    public static int AUTO_CONFIG = 0;

    /**
     * Gets delta angle needed to point at the correct box from this position.
     * CW is positive, CCW is negative.
     *
     * @param angleToPicto Angle at picto between wall and line from picto to robot
     * @param distanceToPicto Distance from robot to picto
     * @return A delta angle
     */
    public static double deltaAngleForBox(double angleToPicto, double distanceToPicto) {
        double x = Math.cos(0);

        return 0;
    }

    public static double distanceForBox(double angleToPicto, double distanceToPicto) {
        return 0;
    }

}
