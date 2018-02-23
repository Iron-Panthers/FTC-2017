package org.firstinspires.ftc.team7316.util;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * The coordinate system is relative to the picto.
 * Picto is love, picto is life. Coordinate system in millimeters and degrees.
 *
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

    private static final double CLOSE_RED_LEFT_X = 100;
    private static final double CLOSE_RED_CENTER_X = 150;
    private static final double CLOSE_RED_RIGHT_X = 200;
    private static final double FAR_RED_LEFT_X = 100;
    private static final double FAR_RED_CENTER_X = 150;
    private static final double FAR_RED_RIGHT_X = 200;
    private static final double CLOSE_BLUE_LEFT_X = 100;
    private static final double CLOSE_BLUE_CENTER_X = 150;
    private static final double CLOSE_BLUE_RIGHT_X = 200;
    private static final double FAR_BLUE_LEFT_X = 100;
    private static final double FAR_BLUE_CENTER_X = 150;
    private static final double FAR_BLUE_RIGHT_X = 200;

    /**
     * Gets delta angle needed to point at the correct box from this position.
     * CW is positive, CCW is negative.
     *
     * @param angleToPicto Angle at picto between wall and line from picto to robot
     * @param xToPicto X delta from picto to robot
     * @param yToPicto Y delta from picto to robot
     * @return A delta angle
     */
    public static double deltaAngleForBox(double angleToPicto, double xToPicto, double yToPicto) {
        double x = Math.cos(0);

        return 0;
    }

    /**
     * Gets distance needed to move from this position to correct column.
     * Robot is placed in front of box so that you need only to outtake.
     *
     * @param angleToPicto Angle at picto between wall and line from picto to robot
     * @param xToPicto X delta from picto to robot
     * @param yToPicto Y delta from picto to robot
     * @return A delta angle
     */
    public static double distanceForBox(double angleToPicto, double xToPicto, double yToPicto) {
        return 0;
    }

}
