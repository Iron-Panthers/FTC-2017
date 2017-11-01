package org.firstinspires.ftc.team7316.util;

/**
 * Created by wayne on 10/21/2016.
 */
public class Constants {

    public static final double JOYSTICK_DRIVE_DEADZONE = 0.03;
    public static final double DRIVER_MOTOR_DEADZONE = 0.18;
    public static final int ENCODER_TICK_PER_REV = 560; //halved due to faster gear ratio
    public static final int DRIVE_RPM_MAX = 280; // can change later actual max 320
    public static final double WHEEL_RADIUS = 2; // I THINK THIS IS IN INCHES
    public static final double WHEEL_CIRCUMFERENCE = WHEEL_RADIUS * 2 * Math.PI;
    public static final double COLOR_DIFFERENCE = 2;

    public static final double DRIVE_SLOW_MULTIPLIER  = 0.7;
    public static final double DRIVE_VERY_SLOW_MULTIPLIER = 0.35;

    //Intake shenanigans
    public static final double INTAKE_SERVO_MAX_POSITION = 0.85;
    public static final double INTAKE_SERVO_MIN_POSITION = 0.35;

    public static final double INTAKE_LIFT_POWER = 0.65;
    public static final double INTAKE_POWER_WEIGHTING = 0.6;

    //Jewel arm shenanigans
    public static final double ARM_SERVO_TRAVEL_TIME = 2;

    //Drive Base PID Constants
    public static final double encoderP = 0;
    public static final double encoderI = 0;
    public static final double encoderD = 0;

    public static final double DISTANCE_ERROR_RANGE = 0.03;

    public static final double FORWARD_MOTOR_DEADZONE = 0.1;
    public static final double STRAFING_MOTOR_DEADZONE = 0.5;
    public static final double TURNING_MOTOR_DEADZONE = 0;

    public static final double gyroP = 0;
    public static final double gyroI = 0;
    public static final double gyroD = 0;

    public static final double sqrt2 = Math.sqrt(2);

    public static double distanceToTicks(double dist) {
        double inches = dist*12;
        double revs = inches/ WHEEL_CIRCUMFERENCE;
        double ticks = revs*ENCODER_TICK_PER_REV;
        return ticks;
    }
}
