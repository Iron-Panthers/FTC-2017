package org.firstinspires.ftc.team7316.modes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.modes.TeleopBaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;

/**
 * Created by jerry on 2/24/18.
 */

@TeleOp(name = "TeleopDriveNoGyro")
public class DriveModeNoGyro extends TeleopBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.inTeleop = true;
        Hardware.gyro_offline = true;
    }

    @Override
    public void onLoop() {

    }
}
