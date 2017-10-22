package org.firstinspires.ftc.team7316.modes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.team7316.modes.BaseOpMode;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;
import org.firstinspires.ftc.team7316.util.input.GamepadAxis;
import org.firstinspires.ftc.team7316.util.input.GamepadWrapper;
import org.firstinspires.ftc.team7316.util.input.JoystickWrapper;
import org.firstinspires.ftc.team7316.util.subsystems.GlyphIntake;
import org.firstinspires.ftc.team7316.util.subsystems.MecanumDriveBase;

import java.util.Locale;

@TeleOp(name = "TeleopDrive")
public class DriveMode extends BaseOpMode {

    //private BNO055IMU imu;
    //private ModernRoboticsI2cGyro gyro;

    private GamepadWrapper gp;
    private GamepadWrapper gp2;
    private MecanumDriveBase driveBase;
    private GlyphIntake glyphIntake;

    // State used for updating telemetry
    Orientation angles;
    Acceleration gravity;

    @Override
    public void onInit() {
        /* // Set up the parameters with which we will use our IMU. Note that integration
        // algorithm here just reports accelerations to the logcat log; it doesn't actually
        // provide positional information.
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        //imu = hardwareMap.get(BNO055IMU.class, "imu");
        //imu.initialize(parameters);
        //gyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro"); */
        Hardware.setHardwareMap(hardwareMap);


        gp = new GamepadWrapper(gamepad1);
        gp2 = new GamepadWrapper(gamepad2);


        driveBase = new MecanumDriveBase();
        glyphIntake = new GlyphIntake(Hardware.instance.intakeServo, Hardware.instance.rightIntakeMotor, Hardware.instance.leftIntakeMotor, gp2.left_stick, gp2.right_stick);

        Scheduler.instance.addTask(driveBase);
        Scheduler.instance.addTask(glyphIntake);

        // Set up our telemetry dashboard
        //composeTelemetry();

        // Start the logging of measured acceleration
        //imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);
    }

    @Override
    public void onLoop() {
        //telemetry.addData("heading", formatAngle(angles.angleUnit, angles.firstAngle));
        //telemetry.addData("heading", gyro.getIntegratedZValue());

        double lX = gp.axisValue(GamepadAxis.L_STICK_X);
        if (Math.abs(lX) < Constants.JOYSTICK_DRIVE_DEADZONE) {
            lX = 0;
        }

        double lY = gp.axisValue(GamepadAxis.L_STICK_Y);
        if (Math.abs(lY) < Constants.JOYSTICK_DRIVE_DEADZONE) {
            lY = 0;
        }

        double rX = gp.axisValue(GamepadAxis.R_STICK_X);
        if (Math.abs(rX) < Constants.JOYSTICK_DRIVE_DEADZONE) {
            rX = 0;
        }

        double turnSpeed = rX;
        double moveDir = Math.atan2(lX, lY);
        double moveSpeed = Math.sqrt(lX*lX + lY*lY);

        driveBase.setWantedOmega(turnSpeed);
        driveBase.setWantedSpeedAndMovementAngle(moveSpeed, moveDir);

        Hardware.log("servo position", Hardware.instance.intakeServo.getPosition());
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
