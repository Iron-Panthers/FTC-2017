package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team7316.util.Constants;

/**
 * Created by jerry on 11/7/17.
 */

@TeleOp(name = "colorsensor")
public class ColorSensorTesting extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        ColorSensor sensor = hardwareMap.colorSensor.get("cs");
        sensor.enableLed(true);
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("r", sensor.red() - Constants.COLOROFFSET_R);
            telemetry.addData("g", sensor.green() - Constants.COLOROFFSET_G);
            telemetry.addData("b", sensor.blue() - Constants.COLOROFFSET_B);
            telemetry.update();
        }
    }
}
