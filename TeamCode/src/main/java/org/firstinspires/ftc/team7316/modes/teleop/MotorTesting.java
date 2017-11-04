package org.firstinspires.ftc.team7316.modes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.modes.BaseOpMode;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveJoystickTesting;
import org.firstinspires.ftc.team7316.util.input.GamepadAxis;
import org.firstinspires.ftc.team7316.util.input.OI;

/**
 * Created by jerry on 11/3/17.
 */

@TeleOp(name = "MotorTesting")
public class MotorTesting extends BaseOpMode {
    @Override
    public void onInit() {
        Scheduler.inTeleop = true;
        Scheduler.instance.add(new DriveJoystickTesting());
    }

    @Override
    public void onLoop() {

    }
}
