package org.firstinspires.ftc.team7316.util;

/**
 * Created by wayne on 10/21/2016.
 */
public class Constants {

    public static final double JOYSTICK_DRIVE_DEADZONE = 0.03;
    public static final double DRIVER_MOTOR_DEADZONE = 0.18;
    public static final int ENCODER_TICK_PER_REV = 1120;
    public static final int DRIVE_RPM_MAX = 280; // can change later actual max 320
    public static final double DISTANCE_PER_REV = 4*Math.PI;
    public static final double COLOR_DIFFERENCE = 2;

    public static final double INTAKE_SERVO_MAX_POSITION = 0.85;
    public static final double INTAKE_SERVO_MIN_POSITION = 0.55;

    //Drive Base PID Constants
    public static final double encoderP = 0;
    public static final double encoderI = 0;
    public static final double encoderD = 0;



    public static final double gyroP = 0;
    public static final double gyroI = 0;
    public static final double gyroD = 0;

    public static final double sqrt2 = Math.sqrt(2);

    public static double distanceToTicks(double dist) {
        double inches = dist*12;
        double revs = inches/DISTANCE_PER_REV;
        double ticks = revs*ENCODER_TICK_PER_REV;
        return ticks;
    }
}
