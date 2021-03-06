package org.firstinspires.ftc.team7316.util;

import android.util.Log;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.team7316.util.sensors.VuforiaCameraWrapper;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The coordinate system is relative to the picto.
 * Picto is love, picto is life. Coordinate system in millimeters and degrees.
 * Make sure to {@link #setConfig(RelicRecoveryVuMark, int)} before trying to use functions.
 *
 * Created by andrew on 2/22/18.
 */
public class CryptoLocations {

    public static final double RED_TURN_TO_PICTO = 50;
    public static final double BLUE_TURN_TO_PICTO = -50;

    public static final int CLOSE_RED_AUTO = 0;
    public static final int FAR_RED_AUTO = 1;
    public static final int CLOSE_BLUE_AUTO = 2;
    public static final int FAR_BLUE_AUTO = 3;

    private static ArrayList<RelicRecoveryVuMark> all;
    public static void resetCryptoLocations() {
        all = new ArrayList<>(Arrays.asList(new RelicRecoveryVuMark[]{
                RelicRecoveryVuMark.RIGHT,
                RelicRecoveryVuMark.CENTER,
                RelicRecoveryVuMark.LEFT,
                RelicRecoveryVuMark.UNKNOWN}));
    }

    private static RelicRecoveryVuMark PICTO_LOCATION = RelicRecoveryVuMark.UNKNOWN;
    private static int AUTO_CONFIG = 0;

    //  distances to crypto are measured from the balancing stone
    private static final double CLOSE_RED_LEFT_X = 865;
    private static final double CLOSE_RED_CENTER_X = 700;
    private static final double CLOSE_RED_RIGHT_X = 490;
    private static final double CLOSE_RED_Y = 130;

    private static final double FAR_RED_LEFT_Y = 1050;
    private static final double FAR_RED_CENTER_Y = 890;
    private static final double FAR_RED_RIGHT_Y = 710;
    private static final double FAR_RED_X = 700;

    private static final double CLOSE_BLUE_LEFT_X = 955;
    private static final double CLOSE_BLUE_CENTER_X = 1158;
    private static final double CLOSE_BLUE_RIGHT_X = 1361;
    private static final double CLOSE_BLUE_Y = 130;

    private static final double FAR_BLUE_LEFT_Y = -710;
    private static final double FAR_BLUE_CENTER_Y = -914;
    private static final double FAR_BLUE_RIGHT_Y = -1116;
    private static final double FAR_BLUE_X = 850;

    //  how far away from the wall the robot will stop
    private static final double SPACING_DISTANCE = 245;

    private static double DX_TO_COLUMN = 0;
    private static double DY_TO_COLUMN = 0;
    private static double PHONE_Z_OFFSET = 200;
    private static double PHONE_X_OFFSET = 220;

    /**
     *  Gets delta angle needed to point at the correct box from this position.
     * CW is positive, CCW is negative.
     *
     * @param camAngle Angle of camera to picto in degrees
     * @param zNormalToPicto Distance from cam line to picto in mm
     * @param xFromNormal Distance from camera to cam normal in mm
     * @return Angle from robot to correct crypto location
     */
    public static double angleForBox(double camAngle, double zNormalToPicto, double xFromNormal) {
        zNormalToPicto = Math.abs(zNormalToPicto) + PHONE_Z_OFFSET;
        xFromNormal += PHONE_X_OFFSET;
        camAngle = camAngle * Math.PI/180;

        double angleToPicto = camAngle - Math.atan2(xFromNormal, zNormalToPicto);
        Hardware.log("angle to picto", angleToPicto * 180 / Math.PI);

        double zToPicto = Math.sqrt(zNormalToPicto*zNormalToPicto + xFromNormal*xFromNormal);
        double xToPicto = Math.sin(angleToPicto)*zToPicto;
        double yToPicto = Math.cos(angleToPicto)*zToPicto;

        double dXToBox = xToPicto - DX_TO_COLUMN;
        double dYToBox = yToPicto - DY_TO_COLUMN;

        Hardware.log("dXToBox", dXToBox);
        Hardware.log("dYToBos", dYToBox);

        double angleToDest = -Math.toDegrees(Math.atan2(dYToBox, dXToBox));
        Hardware.log("anlge pre adjust", angleToDest);
        if (AUTO_CONFIG == CLOSE_RED_AUTO || AUTO_CONFIG == FAR_RED_AUTO) {
            angleToDest += 180;
        }

        return Util.wrap(angleToDest);
    }

    /**
     * Gets distance from current robot location to correct crypto based on
     * pictograph location
     *
     * @param camAngle Angle of camera to picto
     * @param zNormalToPicto Dist from cam line to picto
     * @param xFromNormal Dist from cam normal to cam position along cam line
     * @return Distance from robot location to destination crypto column
     */
    public static double distanceForBox(double camAngle, double zNormalToPicto, double xFromNormal) {
        zNormalToPicto = Math.abs(zNormalToPicto) + PHONE_Z_OFFSET;
        xFromNormal += PHONE_X_OFFSET;
        camAngle = camAngle * Math.PI/180;

//        Hardware.log("adjusted xFromNormal", xFromNormal);
        double angleToPicto = camAngle - Math.atan2(xFromNormal, zNormalToPicto);

        double zToPicto = Math.sqrt(zNormalToPicto*zNormalToPicto + xFromNormal*xFromNormal);
        double xToPicto = Math.sin(angleToPicto)*zToPicto;
        double yToPicto = Math.cos(angleToPicto)*zToPicto;

        double dXToBox = xToPicto - DX_TO_COLUMN;
        double dYToBox = yToPicto - DY_TO_COLUMN;

        double dist = Math.sqrt(dXToBox*dXToBox + dYToBox*dYToBox);

        if (dist < 300 + SPACING_DISTANCE) {
            Log.i("oops", "======= short dist fuck =======");
            Log.i("zNorm", String.valueOf(zNormalToPicto));
            Log.i("xFrom", String.valueOf(xFromNormal));
            Log.i("dx to column", String.valueOf(DX_TO_COLUMN));
            Log.i("dy to column", String.valueOf(DY_TO_COLUMN));
            Log.i("dX to box", String.valueOf(dXToBox));
            Log.i("dY to box", String.valueOf(dYToBox));
            Log.i("dist final", String.valueOf(dist - SPACING_DISTANCE));
        }

        return dist - SPACING_DISTANCE;
    }

    public static void setConfig(RelicRecoveryVuMark PICTO_LOCATION, int AUTO_CONFIG) {
        CryptoLocations.PICTO_LOCATION = PICTO_LOCATION;
        CryptoLocations.AUTO_CONFIG = AUTO_CONFIG;

        if (PICTO_LOCATION == RelicRecoveryVuMark.LEFT) {
            if (AUTO_CONFIG == CryptoLocations.CLOSE_RED_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.CLOSE_RED_LEFT_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.CLOSE_RED_Y;
            }
            else if (AUTO_CONFIG == CryptoLocations.FAR_RED_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.FAR_RED_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.FAR_RED_LEFT_Y;
            }
            else if (AUTO_CONFIG == CryptoLocations.CLOSE_BLUE_AUTO) {
//                CryptoLocations.DX_TO_COLUMN = CryptoLocations.CLOSE_BLUE_LEFT_X;
//                CryptoLocations.DY_TO_COLUMN = CryptoLocations.CLOSE_BLUE_Y;
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.CLOSE_RED_RIGHT_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.CLOSE_RED_Y;
            }
            else if (AUTO_CONFIG == CryptoLocations.FAR_BLUE_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.FAR_BLUE_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.FAR_BLUE_LEFT_Y;
            }
        }
        else if (PICTO_LOCATION == RelicRecoveryVuMark.CENTER || PICTO_LOCATION == RelicRecoveryVuMark.UNKNOWN) {
            if (AUTO_CONFIG == CryptoLocations.CLOSE_RED_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.CLOSE_RED_CENTER_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.CLOSE_RED_Y;
            }
            else if (AUTO_CONFIG == CryptoLocations.FAR_RED_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.FAR_RED_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.FAR_RED_CENTER_Y;
            }
            else if (AUTO_CONFIG == CryptoLocations.CLOSE_BLUE_AUTO) {
//                CryptoLocations.DX_TO_COLUMN = CryptoLocations.CLOSE_BLUE_CENTER_X;
//                CryptoLocations.DY_TO_COLUMN = CryptoLocations.CLOSE_BLUE_Y;
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.CLOSE_RED_CENTER_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.CLOSE_RED_Y;
            }
            else if (AUTO_CONFIG == CryptoLocations.FAR_BLUE_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.FAR_BLUE_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.FAR_BLUE_CENTER_Y;
            }
        }
        else if (PICTO_LOCATION == RelicRecoveryVuMark.RIGHT) {
            if (AUTO_CONFIG == CryptoLocations.CLOSE_RED_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.CLOSE_RED_RIGHT_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.CLOSE_RED_Y;
            }
            else if (AUTO_CONFIG == CryptoLocations.FAR_RED_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.FAR_RED_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.FAR_RED_RIGHT_Y;
            }
            else if (AUTO_CONFIG == CryptoLocations.CLOSE_BLUE_AUTO) {
//                CryptoLocations.DX_TO_COLUMN = CryptoLocations.CLOSE_BLUE_RIGHT_X;
//                CryptoLocations.DY_TO_COLUMN = CryptoLocations.CLOSE_BLUE_Y;
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.CLOSE_RED_LEFT_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.CLOSE_RED_Y;
            }
            else if (AUTO_CONFIG == CryptoLocations.FAR_BLUE_AUTO) {
                CryptoLocations.DX_TO_COLUMN = CryptoLocations.FAR_BLUE_X;
                CryptoLocations.DY_TO_COLUMN = CryptoLocations.FAR_BLUE_RIGHT_Y;
            }
        }
    }

    private static double centerTxForTz(double tZ) {
        return tZ*0.0987 - 18.57; //magic numbers from desmos, just trust them thanks bye
    }

    public static RelicRecoveryVuMark emptyLocation() {
        return all.get(0);
    }

    public static void removeLocation(RelicRecoveryVuMark mark) {
        all.remove(mark);
    }

    public static RelicRecoveryVuMark popLocation() {
        RelicRecoveryVuMark mark = all.get(0);
        all.remove(0);
        return mark;
    }

    public static double distanceForConfig() {
        double x = 325;
        double y = 600;
                //600 mm y
        //400 mm x
        x-= CryptoLocations.DX_TO_COLUMN;
        y-= CryptoLocations.DY_TO_COLUMN;

        return Math.sqrt(x*x+y*y) - SPACING_DISTANCE;//fuck you//;)
    }

}
