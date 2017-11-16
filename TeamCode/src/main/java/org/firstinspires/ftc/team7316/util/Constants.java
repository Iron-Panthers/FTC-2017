package org.firstinspires.ftc.team7316.util;

/**
 * Created by wayne on 10/21/2016.
 */
public class Constants {

    public static final double JOYSTICK_DRIVE_DEADZONE = 0.03;
    public static final double DRIVER_MOTOR_DEADZONE = 0.18;
    public static final int ENCODER_TICK_PER_REV = 560; //halved due to gear ratio
    public static final int DRIVE_RPM_MAX = 280; // can change later actual max 320
    public static final double WHEEL_RADIUS = 2; // I THINK THIS IS IN INCHES
    public static final double WHEEL_CIRCUMFERENCE = WHEEL_RADIUS * 2 * Math.PI;
    public static final double COLOR_DIFFERENCE = 5;

    public static final double DRIVE_SLOW_MULTIPLIER  = 0.7;
    public static final double DRIVE_VERY_SLOW_MULTIPLIER = 0.35;

    //Intake shenanigans
    public static final double INTAKE_SERVO_MAX_POSITION = 0.75;
    public static final double INTAKE_SERVO_MIN_POSITION = 0.45;

    public static final double INTAKE_LIFT_POWER = 0.65;
    public static final double INTAKE_POWER_WEIGHTING = 0.6;

    //Jewel arm shenanigans
    public static final double ARM_SERVO_TRAVEL_TIME = 1.5;

    //Drive Base PID Constants
    public static final double DRIVE_P = 0.005;
    public static final double DRIVE_I = 0;
    public static final double DRIVE_D = 0;

    public static final double DISTANCE_ERROR_RANGE = 0.4; //inches
    public static final double DISTANCE_ERROR_RANGE_TICKS = (double)ENCODER_TICK_PER_REV / WHEEL_CIRCUMFERENCE * DISTANCE_ERROR_RANGE;

    public static final double FORWARD_MOTOR_DEADZONE = 0.1;
    public static final double STRAFING_MOTOR_DEADZONE = 0.5;
    public static final double TURNING_MOTOR_DEADZONE = 0;

    public static final double GYRO_P = 0.05;
    public static final double GYRO_I = 0.1;
    public static final double GYRO_D = 0.1;

    public static final double sqrt2 = Math.sqrt(2);

    //Key is from Jerry's Vuforia dev account
    public static final String vuforiaLicenseKey = "AX5kYPX/////AAAAGU3PfsyXBULLmvcBPSA/sq8MU9VRtH0JkRzhv6Gggr2CpIl9G4uMhuk/GpUW7pgNKluG8PpL85nQo2AakItuDJUgOkCwK6w0YHQPx6+rf8jZM98Fp1lcmH85r/w2JyjVZB43mQAGuyrlJMi24YR9n6m93YNrtv710/h8DuurXnKBtn2ucrsyUjAVfKJzlIXrAB7sZ8MZDqA1rWD+GqoO5pWAW2sobpl64F4A1Fzf+Zzn340wOoH6UEHTyRb1clkSezxvc129fij+4Ev5jOJioiFJyCcF7YXY9zczVpyByqad0w+HqAR2VXj8hKBgL6SRZ6yQ5GmrUY1/5JUQXiMwdRfT5RKjHMlqKP9f9J1x/V7l";

    public static double distanceToTicks(double dist) {
        double inches = dist*12;
        double revs = inches/ WHEEL_CIRCUMFERENCE;
        double ticks = revs*ENCODER_TICK_PER_REV;
        return ticks;
    }
}
