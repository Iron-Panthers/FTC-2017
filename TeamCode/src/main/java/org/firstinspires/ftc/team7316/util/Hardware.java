package org.firstinspires.ftc.team7316.util;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
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

    private static final String GYRO_NAME = "gyro";

    public DcMotor leftFrontDriveMotor;
    public DcMotor rightFrontDriveMotor;
    public DcMotor leftBackDriveMotor;
    public DcMotor rightBackDriveMotor;

    public DcMotor rightIntakeMotor;
    public DcMotor leftIntakeMotor;

    public Servo intakeServo;

    public BNO055IMU gyro;

    public Hardware (HardwareMap map) {

        leftFrontDriveMotor = map.dcMotor.get(LEFT_FRONT_DRIVE_MOTOR_NAME);
        leftFrontDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFrontDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightFrontDriveMotor = map.dcMotor.get(RIGHT_FRONT_DRIVE_MOTOR_NAME);
        rightFrontDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftBackDriveMotor = map.dcMotor.get(LEFT_BACK_DRIVE_MOTOR_NAME);
        leftBackDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightBackDriveMotor = map.dcMotor.get(RIGHT_BACK_DRIVE_MOTOR_NAME);
        rightBackDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightIntakeMotor = map.dcMotor.get(RIGHT_INTAKE_MOTOR_NAME);
        rightIntakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftIntakeMotor = map.dcMotor.get(LEFT_INTAKE_MOTOR_NAME);
        leftIntakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intakeServo = map.servo.get(INTAKE_SERVO_NAME);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        gyro = map.get(BNO055IMU.class, GYRO_NAME);

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
