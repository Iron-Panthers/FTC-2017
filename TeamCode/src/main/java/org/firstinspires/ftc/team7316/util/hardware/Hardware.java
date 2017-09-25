package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

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

    private static final String GYRO_NAME = "gyro";

    public DcMotor leftFrontDriveMotor;
    public DcMotor rightFrontDriveMotor;
    public DcMotor leftBackDriveMotor;
    public DcMotor rightBackDriveMotor;

    public ModernRoboticsI2cGyro gyro;

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

        //gyro = map.get(ModernRoboticsI2cGyro.class, GYRO_NAME);

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
