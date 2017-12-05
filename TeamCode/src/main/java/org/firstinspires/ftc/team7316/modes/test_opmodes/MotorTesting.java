package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.modes.TelopBaseOpMode;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveJoystickTesting;

/**
 * Created by jerry on 11/3/17.
 */

@TeleOp(name = "MotorTesting")
public class MotorTesting extends TelopBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.inTeleop = true;
        Scheduler.instance.add(new DriveJoystickTesting());
    }

    @Override
    public void onLoop() {

    }
}
