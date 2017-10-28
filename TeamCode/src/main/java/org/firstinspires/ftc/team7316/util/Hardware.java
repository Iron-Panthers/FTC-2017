package org.firstinspires.ftc.team7316.util;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by andrew on 9/15/16.
 */

public class Hardware {

    public static Hardware instance = null;

    public static final String tag = "IronPanthers";

    private static Telemetry telemetry;

    private static final String LEFT_FRONT_DRIVE_MOTOR_NAME = "mdfl";
    private static final String RIGHT_FRONT_DRIVE_MOTOR_NAME = "mdfr";
    private static final String LEFT_BACK_DRIVE_MOTOR_NAME = "mdbl";
    private static final String RIGHT_BACK_DRIVE_MOTOR_NAME = "mdbr";

    private static final String RIGHT_INTAKE_MOTOR_NAME = "rim";
    private static final String LEFT_INTAKE_MOTOR_NAME = "lim";
    private static final String INTAKE_LIFT_MOTOR_NAME = "ilm";

    private static final String INTAKE_SERVO_NAME = "is";

    private static final String RELIC_ARM_SERVO_NAME = "ras";
    private static final String RELIC_GRABBER_SERVO_NAME = "rgs";

    private static final String COLOR_SENSOR_NAME = "cs";

    private static final String GYRO_NAME = "gyro";

    public DcMotor leftFrontDriveMotor;
    public DcMotor rightFrontDriveMotor;
    public DcMotor leftBackDriveMotor;
    public DcMotor rightBackDriveMotor;

    public DcMotor rightIntakeMotor;
    public DcMotor leftIntakeMotor;

    public DcMotor intakeLiftMotor;

    public Servo intakeServo;

    public Servo relicGrabberServo;
    public CRServo relicArmServo;

    public ColorSensor colorsensor;

    public Hardware (HardwareMap map) {

        //drive motors
        leftFrontDriveMotor = map.dcMotor.get(LEFT_FRONT_DRIVE_MOTOR_NAME);
        leftFrontDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFrontDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFrontDriveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightFrontDriveMotor = map.dcMotor.get(RIGHT_FRONT_DRIVE_MOTOR_NAME);
        rightFrontDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDriveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftBackDriveMotor = map.dcMotor.get(LEFT_BACK_DRIVE_MOTOR_NAME);
        leftBackDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBackDriveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightBackDriveMotor = map.dcMotor.get(RIGHT_BACK_DRIVE_MOTOR_NAME);
        rightBackDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDriveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //intake hardware
        rightIntakeMotor = map.dcMotor.get(RIGHT_INTAKE_MOTOR_NAME);
        rightIntakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftIntakeMotor = map.dcMotor.get(LEFT_INTAKE_MOTOR_NAME);
        leftIntakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intakeLiftMotor = map.dcMotor.get(INTAKE_LIFT_MOTOR_NAME);
        intakeLiftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intakeServo = map.servo.get(INTAKE_SERVO_NAME);
        intakeServo.setDirection(Servo.Direction.REVERSE);
        intakeServo.scaleRange(Constants.INTAKE_SERVO_MIN_POSITION, Constants.INTAKE_SERVO_MAX_POSITION);

        //relic grabber hardware
        //relicGrabberServo = map.servo.get(RELIC_GRABBER_SERVO_NAME);
        //relicArmServo = map.crservo.get(RELIC_ARM_SERVO_NAME);

        //relicGrabberServo = map.servo.get(RELIC_GRABBER_SERVO_NAME);
        //relicArmServo = map.crservo.get(RELIC_ARM_SERVO_NAME);

        //other hardware
        //colorsensor = map.colorSensor.get(COLOR_SENSOR_NAME);

        //Scheduler.instance.addTask(frontSideInfaredSensor);
    }

    public static void setHardwareMap(HardwareMap map) {
        instance = new Hardware(map);
    }

    public static void setTelemetry(Telemetry telemetry) {
        Hardware.telemetry = telemetry;
    }

    public static void log(String caption, Object value) {
        if (telemetry != null) {
            telemetry.addData(caption, value);
        }
    }

}