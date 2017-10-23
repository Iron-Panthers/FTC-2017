package org.firstinspires.ftc.team7316.modes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.team7316.modes.BaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;

import java.util.Locale;

@TeleOp(name = "TeleopDrive")
public class DriveMode extends BaseOpMode {

    //private BNO055IMU imu;
    //private ModernRoboticsI2cGyro gyro;

    // State used for updating telemetry
    Orientation angles;
    Acceleration gravity;

    @Override
    public void onInit() {
        Hardware.setHardwareMap(hardwareMap);

        // Set up our telemetry dashboard
        //composeTelemetry();

        // Start the logging of measured acceleration
        //imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);
    }

    @Override
    public void onLoop() {
        //telemetry.addData("heading", formatAngle(angles.angleUnit, angles.firstAngle));
        //telemetry.addData("heading", gyro.getIntegratedZValue());
    }

    //----------------------------------------------------------------------------------------------
    // Formatting
    //----------------------------------------------------------------------------------------------

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}
