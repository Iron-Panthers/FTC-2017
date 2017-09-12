package org.firstinspires.ftc.team7316.util;

/**
 * Created by wayne on 10/21/2016.
 */
public class Constants {

    public static final float JOYSTICK_DRIVE_DEADZONE = 0.03f;
    public static final float DRIVER_MOTOR_DEADZONE = 0.18f;
    public static final int ENCODER_TICK_PER_REV = 1120;
    public static final double DISTANCE_PER_REV = 4*Math.PI;
    public static final double COLOR_DIFFERENCE = 2;

    //Drive Base PID Constants
    public static final float encoderP = 0;
    public static final float encoderI = 0;
    public static final float encoderD = 0;

    public static final float gyroP = 0;
    public static final float gyroI = 0;
    public static final float gyroD = 0;

    public static double distanceToTicks(double dist) {
        double inches = dist*12;
        double revs = inches/DISTANCE_PER_REV;
        double ticks = revs*ENCODER_TICK_PER_REV;
        return ticks;
    }

}
