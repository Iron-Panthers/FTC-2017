package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.modes.TeleopBaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveJoystickTesting;

/**
 * Created by jerry on 11/3/17.
 */

@TeleOp(name = "MotorTesting")
public class MotorTesting extends TeleopBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.inTeleop = false;
    }

    @Override
    public void onLoop() {
        Hardware.log("ticks", Hardware.instance.frontLeftDriveMotor.getCurrentPosition());
    }
}
