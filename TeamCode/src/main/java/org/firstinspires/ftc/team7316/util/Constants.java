package org.firstinspires.ftc.team7316.util;

/**
 * Created by wayne on 10/21/2016.
 */
public class Constants {

    public static final double JOYSTICK_DRIVE_DEADZONE = 0.03;
    public static final double DRIVER_MOTOR_DEADZONE = 0.18;
    public static final int ENCODER_TICK_PER_REV = 1120; //halved due to gear ratio
    public static final int ENCODER_REV_PER_WHEEL_REV = 2;
    public static final int DRIVE_RPM_MAX = 280; // can change later actual max 320
    public static final double WHEEL_RADIUS = 2; // I THINK THIS IS IN INCHES
    public static final double WHEEL_CIRCUMFERENCE = WHEEL_RADIUS * 2 * Math.PI;
    public static final double COLOR_DIFFERENCE = 5;

    public static final double DRIVE_SLOW_MULTIPLIER  = 0.7;
    public static final double DRIVE_VERY_SLOW_MULTIPLIER = 0.35;

    //Intake shenanigans
    public static final double INTAKE_SERVO_MAX_POSITION = 1;
    public static final double INTAKE_SERVO_MIN_POSITION = 0.2;

    public static final double INTAKE_LIFT_POWER = 0.65;
    public static final double INTAKE_POWER_WEIGHTING = 0.6;


    //-----------Auto constants-----------//
    //drive distances
    public static final double JEWEL_WHACK_DISTANCE = 3.5; //inches
    public static final double PARKING_FOWARD_DISTANCE = 13; //inches after driving for time after off the balancing pad
    public static final double CRYPTOBOX_STRAFE_DISTANCE = 11.5;//inches too
    //drive times(temporary) and their powers
    public static final double OFF_PAD_TIME = 1;
    public static final double DRIVE_FORWARD_TIME = 1.6; //driving off the pad and to the parking also for now
    public static final double FORWARD_POWER_TIME = 0.3;
    public static final double STRAFE_LEFT_TIME = 1;
    public static final double LEFT_POWER_TIME = 0.8;

    //balancing pad offsets after whacking jewel
    public static final double DISTANCE_PAD_OFFSET = 1; //inches offset after jewel
    public static final int DISTANCE_PAD_OFFSET_TICKS = (int)(inchesToTicks(DISTANCE_PAD_OFFSET));
    public static final double TIME_PAD_OFFSET = 0.2; //seconds offset after jewel

    //Jewel arm shenanigans
    public static final double ARM_SERVO_TRAVEL_TIME = 1;

    //Drive Base PID Constants
    //tested 11/17
    public static final double DRIVE_P = 0.0025;
    public static final double DRIVE_I = 0.0004;
    public static final double DRIVE_D = 0.004;

    public static final double STRAIGHT_DRIVE_MAXSPEED = 0.65;

    public static final double DISTANCE_ERROR_RANGE = 0.4; //inches
    public static final double DISTANCE_ERROR_RANGE_TICKS = (double)ENCODER_TICK_PER_REV / ENCODER_REV_PER_WHEEL_REV / WHEEL_CIRCUMFERENCE * DISTANCE_ERROR_RANGE;

    public static final double FORWARD_MOTOR_DEADZONE = 0.1;
    public static final double STRAFING_MOTOR_DEADZONE = 0.5;
    public static final double TURNING_MOTOR_DEADZONE = 0;

    public static final double GYRO_P = 0.05;
    public static final double GYRO_I = 0.1;
    public static final double GYRO_D = 0.1;

    public static final double sqrt2 = Math.sqrt(2);

    //distance in inches
    public static double inchesToTicks(double dist) {
        return (double)ENCODER_TICK_PER_REV / ENCODER_REV_PER_WHEEL_REV / WHEEL_CIRCUMFERENCE * dist;
    }
}
