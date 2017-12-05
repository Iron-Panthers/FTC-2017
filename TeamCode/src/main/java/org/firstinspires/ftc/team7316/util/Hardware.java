package org.firstinspires.ftc.team7316.util;

import android.content.Context;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team7316.util.motorwrappers.DCMotorWrapper;
import org.firstinspires.ftc.team7316.util.sensors.ColorWrapper;
import org.firstinspires.ftc.team7316.util.sensors.GyroWrapper;
import org.firstinspires.ftc.team7316.util.sensors.VuforiaCameraWrapper;

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

    private static final String RIGHT_JEWEL_ARM_NAME = "rja";
    private static final String COLOR_SENSOR_NAME = "cs";

    private static final String GYRO_NAME = "gyro";

    public static boolean colorsensor_offline = false;
    public static boolean gyro_offline = false;

    public DcMotor frontLeftDriveMotor;
    public DcMotor frontRightDriveMotor;
    public DcMotor backLeftDriveMotor;
    public DcMotor backRightDriveMotor;

    public DCMotorWrapper frontLeftDriveMotorWrapper;
    public DCMotorWrapper frontRightDriveMotorWrapper;
    public DCMotorWrapper backLeftDriveMotorWrapper;
    public DCMotorWrapper backRightDriveMotorWrapper;

    public DcMotor rightIntakeMotor;
    public DcMotor leftIntakeMotor;

    public DcMotor intakeLiftMotor;

    public Servo intakeServo;

    public Servo relicGrabberServo;
    public CRServo relicArmServo;

    public Servo rightJewelArm;
    public ColorSensor colorsensor;
    public ColorWrapper colorWrapper;

    public VuforiaCameraWrapper vuforiaCameraWrapper;

//    public BNO055IMU gyro;
//    public GyroWrapper gyroWrapper;

    public Hardware (HardwareMap map) {

        //drive motors
        frontLeftDriveMotor = map.dcMotor.get(LEFT_FRONT_DRIVE_MOTOR_NAME);
        frontLeftDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftDriveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDriveMotorWrapper = new DCMotorWrapper(frontLeftDriveMotor, new PID(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D, Constants.DRIVE_F));

        frontRightDriveMotor = map.dcMotor.get(RIGHT_FRONT_DRIVE_MOTOR_NAME);
        frontRightDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightDriveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDriveMotorWrapper = new DCMotorWrapper(frontRightDriveMotor, new PID(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D, Constants.DRIVE_F));

        backLeftDriveMotor = map.dcMotor.get(LEFT_BACK_DRIVE_MOTOR_NAME);
        backLeftDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftDriveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDriveMotorWrapper = new DCMotorWrapper(backLeftDriveMotor, new PID(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D, Constants.DRIVE_F));

        backRightDriveMotor = map.dcMotor.get(RIGHT_BACK_DRIVE_MOTOR_NAME);
        backRightDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightDriveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDriveMotorWrapper = new DCMotorWrapper(backRightDriveMotor, new PID(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D, Constants.DRIVE_F));

        //intake hardware
        rightIntakeMotor = map.dcMotor.get(RIGHT_INTAKE_MOTOR_NAME);
        rightIntakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftIntakeMotor = map.dcMotor.get(LEFT_INTAKE_MOTOR_NAME);
        leftIntakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intakeLiftMotor = map.dcMotor.get(INTAKE_LIFT_MOTOR_NAME);
        intakeLiftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intakeServo = map.servo.get(INTAKE_SERVO_NAME);
        intakeServo.setDirection(Servo.Direction.REVERSE);
        //intakeServo.scaleRange(Constants.INTAKE_SERVO_MIN_POSITION, Constants.INTAKE_SERVO_MAX_POSITION);

//        relic grabber hardware
//        relicGrabberServo = map.servo.get(RELIC_GRABBER_SERVO_NAME);
//        relicArmServo = map.crservo.get(RELIC_ARM_SERVO_NAME);
//
//        relicGrabberServo = map.servo.get(RELIC_GRABBER_SERVO_NAME);
//        relicArmServo = map.crservo.get(RELIC_ARM_SERVO_NAME);

        //jewel arm hardware
        rightJewelArm = map.servo.get(RIGHT_JEWEL_ARM_NAME);
        try {
            colorsensor = map.colorSensor.get(COLOR_SENSOR_NAME);
            colorsensor.enableLed(true);
            colorWrapper = new ColorWrapper(colorsensor);
            colorsensor_offline = false;
        }
        catch (Exception e) {
            colorsensor_offline = true;
        }

        //gyro shenanigans
        BNO055IMU.Parameters gyroParams = new BNO055IMU.Parameters();
        gyroParams.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        gyroParams.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        gyroParams.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        gyroParams.loggingEnabled      = true;
        gyroParams.loggingTag          = "IMU";
        gyroParams.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

//        try {
//            gyro = map.get(BNO055IMU.class, GYRO_NAME);
//            gyro.initialize(gyroParams);
//            gyroWrapper = new GyroWrapper(gyro);
//            gyro_offline = false;
//        }
//        catch (Exception e) {
//            gyro_offline = true;
//        }

        vuforiaCameraWrapper = new VuforiaCameraWrapper();

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