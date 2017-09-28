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

    //Key is from Jerry's Vuforia dev account
    public static final String vuforiaLicenseKey = "AX5kYPX/////AAAAGU3PfsyXBULLmvcBPSA/sq8MU9VRtH0JkRzhv6Gggr2CpIl9G4uMhuk/GpUW7pgNKluG8PpL85nQo2AakItuDJUgOkCwK6w0YHQPx6+rf8jZM98Fp1lcmH85r/w2JyjVZB43mQAGuyrlJMi24YR9n6m93YNrtv710/h8DuurXnKBtn2ucrsyUjAVfKJzlIXrAB7sZ8MZDqA1rWD+GqoO5pWAW2sobpl64F4A1Fzf+Zzn340wOoH6UEHTyRb1clkSezxvc129fij+4Ev5jOJioiFJyCcF7YXY9zczVpyByqad0w+HqAR2VXj8hKBgL6SRZ6yQ5GmrUY1/5JUQXiMwdRfT5RKjHMlqKP9f9J1x/V7l";

    public static double distanceToTicks(double dist) {
        double inches = dist*12;
        double revs = inches/DISTANCE_PER_REV;
        double ticks = revs*ENCODER_TICK_PER_REV;
        return ticks;
    }

}
