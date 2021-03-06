package org.firstinspires.ftc.team7316.util;

/**
 * Created by wayne on 10/21/2016.
 */
public class Constants {

    public static final double SQRT2 = Math.sqrt(2);
    public static final double RADIAN_TO_DEGREES = 180 / Math.PI;
    public static final double DEGREES_TO_RADIANS = Math.PI / 180;

    // TODO: Calibrate lock
    public static final double INTAKE_LOCK_MAX_POSITION = 0.7;
    public static final double INTAKE_LOCK_MIN_POSITION = 0.4;

    public static final double JOYSTICK_DRIVE_DEADZONE = 0.05;
    public static final double DRIVER_MOTOR_DEADZONE = 0.1;
    public static final double ENCODER_TICK_PER_REV = 1120; //halved due to gear ratio
    public static final double ENCODER_REV_PER_WHEEL_REV = 0.5;
    public static final int DRIVE_RPM_MAX = 280; // can change later actual max 320
    public static final double WHEEL_RADIUS = 2; // I THINK THIS IS IN INCHES
    public static final double WHEEL_CIRCUMFERENCE = WHEEL_RADIUS * 2 * Math.PI;

    public static final double DRIVE_SLOW_MULTIPLIER  = 0.75;
    public static final double DRIVE_VERY_SLOW_MULTIPLIER = 0.35;

    //Intake shenanigans
    public static final double INTAKE_SERVO_MIN_POSITION = 0.4;
    public static final double INTAKE_SERVO_MAX_POSITION = 1;
    public static final double INTAKE_SERVO_OPEN_POSITION = 0.8;
    public static final double INTAKE_CLAMP_GLYPH_POSITION = 0.15;
    public static final double INTAKE_CLAMP_GLYPH_STRONG_POSITION = 0.1;

    public static final double INTAKE_LIFT_POWER = 1;
    public static final double INTAKE_LEFT_POWER_WEIGHTING = 0.85;
    public static final double INTAKE_RIGHT_POWER_WEIGHTING = 0.85;

    //-----------Auto constants-----------//
    public static final double COAST_TICKS_PER_SECOND = 500;//rough estimate 1670
    public static final double TICKS_PER_SECOND_HALFPOWER = 1400;

    public static final double ROTATIONS_PERFIVESECONDS = 3.5; //all full speedy
    public static final double ROTATIONS_PER_SECOND = ROTATIONS_PERFIVESECONDS / 5;
    public static final double ROTATIONS_180_DEGREES = 0.8;
    public static final double ROTATIONS_90_DEGREES = 0.6;

    public static final double DEGREES_PER_SECOND_COAST = 180;
    public static final double DEGREES_PER_SECOND_HALFPOWER = 95;

    //drive distances
    public static final double JEWEL_WHACK_DISTANCE = 3.5; //inches
    public static final double FAR_CRYPTO_DISTANCE = 8; //inches after driving for time after off the balancing pad
    public static final double CRYPTOBOX_STRAFE_DISTANCE = 11.25;//inches too
    public static final double CLOSE_CRYPTO_DISTANCE = 10;
    public static final double CLOSE_CRYPTO_APPROACH_RED = 7.5;
    public static final double CLOSE_CRYPTO_APPROACH_BLUE = 8.5;
    public static final double FAR_CRYPTO_APPROACH_RED = 4;
    public static final double FAR_CRYPTO_APPROACH_BLUE = 4;
    //INCHES INCHESSSSS
    public static final double RIGHT_COLUMN_DISTANCE_CLOSE = 9;
    public static final double MIDDLE_COLUMN_DISTANCE_CLOSE = 16.5;
    public static final double LEFT_COLUMN_DISTANCE_CLOSE = 24;

    public static final double CLOSE_COLUMN_DISTANCE_FAR = 8;
    public static final double MIDDLE_COLUMN_DISTANCE_FAR = 15;
    public static final double FAR_COLUMN_DISTANCE_FAR = 23;

    public static final double MULTIGLYPH_BACKUP_DISTANCE = 10;
    public static final double MULTIGLYPH_DIST_TO_PIT = 15;
    public static final double MULTIGLYPH_DIST_TO_COLLECT = 20;
    public static final int MAX_TICKS_TO_MULTIGLYPH_CLOSE = inchesToTicks(40);
    public static final int MAX_TICKS_TO_MULTIGLYPH_FAR = inchesToTicks(50);

    public static final double PIT_TO_CRYPTO_CLOSE = 76;  //inches
    public static final double COLUMN_SEPARATION = 6;

    public static final double PLATE_TO_CRYPTO_FAR = 18;

    //in degrees (rudimentary values for now)
    public static final double CLOSE_COLUMN_ANGLE_FAR = Math.atan(Constants.CLOSE_COLUMN_DISTANCE_FAR / Constants.PLATE_TO_CRYPTO_FAR) * Constants.RADIAN_TO_DEGREES;
    public static final double MIDDLE_COLUMN_ANGLE_FAR = Math.atan(Constants.MIDDLE_COLUMN_DISTANCE_FAR / Constants.PLATE_TO_CRYPTO_FAR) * Constants.RADIAN_TO_DEGREES;
    public static final double FAR_COLUMN_ANGLE_FAR = Math.atan(Constants.FAR_COLUMN_DISTANCE_FAR / Constants.PLATE_TO_CRYPTO_FAR) * Constants.RADIAN_TO_DEGREES;

    //drive times(temporary) and their powers
    public static final double RED_OFF_PAD_TIME = 2;
    public static final double BLUE_OFF_PAD_TIME = 1.7;
    public static final double DRIVE_FORWARD_TIME = 1.6; //driving off the pad and to the parking also for now

    public static final double OFF_PAD_POWER = 0.25;
    public static final double FORWARD_POWER_FOR_TIME = 0.1; //was 0.3 whne jewel waacked forward
    public static final double BACKWARD_POWER_FOR_TIME = 0.15; //when  jewel wacked backwards

    public static final double STRAFE_CRYPTO_LEFT_TIME = 1;
    public static final double STRAFE_CRYPTO_CENTER_TIME = 0.85;
    public static final double STRAFE_CRYPTO_RIGHT_TIME = 0.7;
    public static final double LEFT_POWER_TIME = 0.8;

    public static final double OUTTAKE_POWER = -0.7;
    public static final double OUTTAKE_TIME = 0.4;

    //balancing pad offsets after whacking jewel
    public static final double JEWEL_WACK_DELTA = 30;
    public static final double DISTANCE_PAD_OFFSET = 1; //inches offset after jewel hit
    public static final int DISTANCE_PAD_OFFSET_TICKS = (int)(inchesToTicks(DISTANCE_PAD_OFFSET));

    //TILE OFFSETS in relation to the direction hit, not where robot drove
    public static final double TIME_PAD_OFFSET_FORWARD_RED = 0.3; //seconds offset after jewel hit
    public static final double TIME_PAD_OFFSET_FORWARD_BLUE = 0.3;
    public static final double TIME_PAD_OFFSET_BACKWARD_RED = 0.25; //seconds offset after jewel hit
    public static final double TIME_PAD_OFFSET_BACKWARD_BLUE = 0.2; //seconds offset after jewel hit

    //Jewel arm shenanigans
    public static final double ARM_SERVO_TRAVEL_TIME = 1;

    public static final int COLOROFFSET_R = 15;
    public static final int COLOROFFSET_G = 0;
    public static final int COLOROFFSET_B = 0;

    public static final int NO_COLOR_RED = 10;
    public static final int NO_COLOR_GREEN = 20;
    public static final int NO_COLOR_BLUE = 20;

    public static final int MIN_COLOR_VALUE = 10;
    public static final int COLOR_BUFFER_SIZE = 4;
    public static final double COLOR_DIFFERENCE = 1;

    public static final int NO_COLOR_THRESHOLD_RED = NO_COLOR_RED * COLOR_BUFFER_SIZE;
    public static final int NO_COLOR_THRESHOLD_BLUE = NO_COLOR_BLUE * COLOR_BUFFER_SIZE;

    //Drive Base PIDPath Constants
    public static final double DRIVE_P = 0.01;
    public static final double DRIVE_I = 0.0005;
    public static final double DRIVE_D = 0; // 0.0
    public static final double DRIVE_F = 0.5/TICKS_PER_SECOND_HALFPOWER;   //originally 0.00007

    public static final double AUTO_DEADZONE = 0.15;

    public static final double GYRO_P = 0.025;
    public static final double GYRO_I = 0; //original 0.002
    public static final double GYRO_D = 0; //original 0.0004
    public static final double GYRO_F = Util.map(0.5, AUTO_DEADZONE, 1, 0, 1)/DEGREES_PER_SECOND_HALFPOWER; //original 0.00397

    public static final double DISTANCE_ERROR_RANGE = 2.25; //inches
    public static final int DISTANCE_ERROR_RANGE_TICKS = inchesToTicks(DISTANCE_ERROR_RANGE);

    public static final double FORWARD_MOTOR_DEADZONE = 0.1;
    public static final double STRAFING_MOTOR_DEADZONE = 0.5;
    public static final double TURNING_MOTOR_DEADZONE = 0;

    //Key is from Jerry's Vuforia dev account
    public static final String vuforiaLicenseKey = "AX5kYPX/////AAAAGU3PfsyXBULLmvcBPSA/sq8MU9VRtH0JkRzhv6Gggr2CpIl9G4uMhuk/GpUW7pgNKluG8PpL85nQo2AakItuDJUgOkCwK6w0YHQPx6+rf8jZM98Fp1lcmH85r/w2JyjVZB43mQAGuyrlJMi24YR9n6m93YNrtv710/h8DuurXnKBtn2ucrsyUjAVfKJzlIXrAB7sZ8MZDqA1rWD+GqoO5pWAW2sobpl64F4A1Fzf+Zzn340wOoH6UEHTyRb1clkSezxvc129fij+4Ev5jOJioiFJyCcF7YXY9zczVpyByqad0w+HqAR2VXj8hKBgL6SRZ6yQ5GmrUY1/5JUQXiMwdRfT5RKjHMlqKP9f9J1x/V7l";

    //distance in inches
    public static int inchesToTicks(double dist) {
        return (int)(ENCODER_TICK_PER_REV * ENCODER_REV_PER_WHEEL_REV / WHEEL_CIRCUMFERENCE * dist);
    }
    public static int millimetersToTicks(double dist) {
        //lol
        return (int)(ENCODER_TICK_PER_REV * ENCODER_REV_PER_WHEEL_REV / (WHEEL_CIRCUMFERENCE * 25.4) * dist);
    }

    /**
     * The offset from 180 degree turn to go to a different column
     */
    public enum MultiglyphRotate {
        LEFT(Math.atan(COLUMN_SEPARATION / PIT_TO_CRYPTO_CLOSE) * RADIAN_TO_DEGREES),
        CENTER(Math.atan(COLUMN_SEPARATION / PIT_TO_CRYPTO_CLOSE) * RADIAN_TO_DEGREES),
        RIGHT(Math.atan(COLUMN_SEPARATION / PIT_TO_CRYPTO_CLOSE) * RADIAN_TO_DEGREES);

        public final double degrees;

        MultiglyphRotate(double degrees) {
            this.degrees = degrees;
        }
    }
}
