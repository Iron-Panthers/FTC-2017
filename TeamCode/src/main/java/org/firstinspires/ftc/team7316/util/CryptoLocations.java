package org.firstinspires.ftc.team7316.util;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * The coordinate system is relative to the picto.
 * Picto is love, picto is life. Coordinate system in millimeters and degrees.
 * Make sure to {@link #setConfig(RelicRecoveryVuMark, int)} before trying to use functions.
 *
 * Created by andrew on 2/22/18.
 */
public class CryptoLocations {

    public static final double RED_TURN_TO_PICTO = 18;
    public static final double BLUE_TURN_TO_PICTO = 25;

    public static final int CLOSE_RED_AUTO = 0;
    public static final int FAR_RED_AUTO = 1;
    public static final int CLOSE_BLUE_AUTO = 2;
    public static final int FAR_BLUE_AUTO = 3;

    private static RelicRecoveryVuMark PICTO_LOCATION = RelicRecoveryVuMark.UNKNOWN;
    private static int AUTO_CONFIG = 0;

    //  distances to crypto are measured from the balancing stone
    private static final double CLOSE_RED_LEFT_X = 467;
    private static final double CLOSE_RED_CENTER_X = 670;
    private static final double CLOSE_RED_RIGHT_X = 874;

    private static final double FAR_RED_LEFT_Y = 710;
    private static final double FAR_RED_CENTER_Y = 914;
    private static final double FAR_RED_RIGHT_Y = 1116;
    private static final double FAR_RED_X = 975;

    private static final double CLOSE_BLUE_LEFT_X = 955;
    private static final double CLOSE_BLUE_CENTER_X = 1158;
    private static final double CLOSE_BLUE_RIGHT_X = 1361;

    private static final double FAR_BLUE_LEFT_Y = -710;
    private static final double FAR_BLUE_CENTER_Y = -914;
    private static final double FAR_BLUE_RIGHT_Y = -1116;
    private static final double FAR_BLUE_X = 1310;

    //  how far away from the wall the robot will stop
    private static final double SPACING_DISTANCE = 152;

    private static double DX_TO_COLUMN = 0;
    private static double DY_TO_COLUMN = 0;

    /**
     * Gets delta angle needed to point at the correct box from this position.
     * CW is positive, CCW is negative.
     *
     * @param angleOfRobot Bearing of robot
     * @param angleToPicto Delta angle from picto to robot
     * @param zToPicto Z delta from picto to robot
     * @return A delta angle
     */
    public static double deltaAngleForBox(double angleOfRobot, double angleToPicto, double zToPicto) {
        double xToPicto = Math.sin(-angleToPicto)*zToPicto;
        double yToPicto = Math.cos(-angleToPicto)*zToPicto;

        double dXToBox = xToPicto - DX_TO_COLUMN;
        double dYToBox = yToPicto - DY_TO_COLUMN;
        double angleToDest = -Math.toDegrees(Math.atan2(dYToBox, dXToBox));
        if (AUTO_CONFIG == CLOSE_RED_AUTO || AUTO_CONFIG == FAR_RED_AUTO) {
            angleToDest += 180;
        }

        return angleToDest - angleOfRobot;
    }

    /**
     * Gets distance needed to move from this position to correct column.
     * Robot is placed in front of box so that you need only to outtake.
     *
     * @param angleToPicto Angle at picto
     * @param zToPicto Z delta from picto to robot
     * @return A delta angle
     */
    public static double distanceForBox(double angleToPicto, double zToPicto) {
        double xToPicto = Math.sin(-angleToPicto)*zToPicto;
        double yToPicto = Math.cos(-angleToPicto)*zToPicto;

        double dXToBox = xToPicto - DX_TO_COLUMN;
        double dYToBox = yToPicto - DY_TO_COLUMN;

        double dist = Math.sqrt(dXToBox*dXToBox + dYToBox*dYToBox);
        return dist - SPACING_DISTANCE;
    }

    public static void setConfig(RelicRecoveryVuMark PICTO_LOCATION, int AUTO_CONFIG) {
        CryptoLocations.PICTO_LOCATION = PICTO_LOCATION;
        CryptoLocations.AUTO_CONFIG = AUTO_CONFIG;

        if (PICTO_LOCATION == RelicRecoveryVuMark.LEFT) {
            if (AUTO_CONFIG == CryptoLocations.CLOSE_RED_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.CLOSE_RED_LEFT_X;
                CryptoLocations.DY_TO_COLUMN = 0;
            }
            else if (AUTO_CONFIG == CryptoLocations.FAR_RED_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.FAR_RED_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.FAR_RED_LEFT_Y;
            }
            else if (AUTO_CONFIG == CryptoLocations.CLOSE_BLUE_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.CLOSE_BLUE_LEFT_X;
                CryptoLocations.DY_TO_COLUMN = 0;
            }
            else if (AUTO_CONFIG == CryptoLocations.FAR_BLUE_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.FAR_BLUE_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.FAR_BLUE_LEFT_Y;
            }
        }
        else if (PICTO_LOCATION == RelicRecoveryVuMark.CENTER) {
            if (AUTO_CONFIG == CryptoLocations.CLOSE_RED_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.CLOSE_RED_CENTER_X;
                CryptoLocations.DY_TO_COLUMN = 0;
            }
            else if (AUTO_CONFIG == CryptoLocations.FAR_RED_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.FAR_RED_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.FAR_RED_CENTER_Y;
            }
            else if (AUTO_CONFIG == CryptoLocations.CLOSE_BLUE_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.CLOSE_BLUE_CENTER_X;
                CryptoLocations.DY_TO_COLUMN = 0;
            }
            else if (AUTO_CONFIG == CryptoLocations.FAR_BLUE_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.FAR_BLUE_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.FAR_BLUE_CENTER_Y;
            }
        }
        else if (PICTO_LOCATION == RelicRecoveryVuMark.RIGHT) {
            if (AUTO_CONFIG == CryptoLocations.CLOSE_RED_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.CLOSE_RED_RIGHT_X;
                CryptoLocations.DY_TO_COLUMN = 0;
            }
            else if (AUTO_CONFIG == CryptoLocations.FAR_RED_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.FAR_RED_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.FAR_RED_RIGHT_Y;
            }
            else if (AUTO_CONFIG == CryptoLocations.CLOSE_BLUE_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.CLOSE_BLUE_RIGHT_X;
                CryptoLocations.DY_TO_COLUMN = 0;
            }
            else if (AUTO_CONFIG == CryptoLocations.FAR_BLUE_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.FAR_BLUE_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.FAR_BLUE_RIGHT_Y;
            }
        }
    }

}
