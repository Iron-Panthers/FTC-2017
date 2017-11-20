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
    public static final double INTAKE_SERVO_MAX_POSITION = 0.8;
    public static final double INTAKE_SERVO_MIN_POSITION = 0.4;

    public static final double INTAKE_LIFT_POWER = 0.65;
    public static final double INTAKE_POWER_WEIGHTING = 0.6;


    //-----------Auto constants-----------//
    public static final double ROTATIONS_PERFIVESECONDS = 3.5; //all full speedy
    public static final double ROTATIONS_PER_SECOND = ROTATIONS_PERFIVESECONDS / 5;
    public static final double ROTATIONS_180_DEGREES = 0.8;
    public static final double ROTATIONS_90_DEGREES = 0.6;
    //drive distances
    public static final double JEWEL_WHACK_DISTANCE = 3.5; //inches
    public static final double FAR_CRYPTO_DISTANCE = 9; //inches after driving for time after off the balancing pad
    public static final double CRYPTOBOX_STRAFE_DISTANCE = 11.25;//inches too
    public static final double CLOSE_CRYPTO_DISTANCE = 10;
    public static final double CLOSE_CRYPTO_APPROACH = 7;
    //drive times(temporary) and their powers
    public static final double RED_OFF_PAD_TIME = 0.85;
    public static final double BLUE_OFF_PAD_TIME = 1.1;
    public static final double DRIVE_FORWARD_TIME = 1.6; //driving off the pad and to the parking also for now
    public static final double FORWARD_POWER_FOR_TIME = 0.3; //was 0.3 whne jewel waacked forward
    public static final double BACKWARD_POWER_FOR_TIME = 0.4; //when  jewel wacked backwards
    public static final double STRAFE_LEFT_TIME = 0.85;
    public static final double LEFT_POWER_TIME = 0.8;

    public static final double OUTTAKE_POWER = -0.7;
    public static final double OUTTAKE_TIME = 1;

    //balancing pad offsets after whacking jewel
    public static final double DISTANCE_PAD_OFFSET = 1; //inches offset after jewel hit
    public static final int DISTANCE_PAD_OFFSET_TICKS = (int)(inchesToTicks(DISTANCE_PAD_OFFSET));

    //TILE OFFSETS in relation to the direction hit, not where robot drove
    public static final double TIME_PAD_OFFSET_FORWARD_RED = 0.3; //seconds offset after jewel hit
    public static final double TIME_PAD_OFFSET_FORWARD_BLUE = 0.3;
    public static final double TIME_PAD_OFFSET_BACKWARD_RED = 0.25; //seconds offset after jewel hit
    public static final double TIME_PAD_OFFSET_BACKWARD_BLUE = 0.2; //seconds offset after jewel hit

    //Jewel arm shenanigans
    public static final double ARM_SERVO_TRAVEL_TIME = 1;

    //Drive Base PID Constants
    //tested 11/17
    public static final double DRIVE_P = 0.0025; // 0.0025 original
    public static final double DRIVE_I = 0.0004; // 0.0004 origial
    public static final double DRIVE_D = 0.012; // 0.004 original

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
