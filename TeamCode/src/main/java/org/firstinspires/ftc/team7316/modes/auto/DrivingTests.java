package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveForTime;

/**
 * Created by jerry on 11/10/17.
 */

@Autonomous(name = "nononononono")
public class DrivingTests extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.inTeleop = false;
        Scheduler.instance.add(new DriveForTime(Constants.FORWARD_POWER_TIME, 0, Constants.OFF_PAD_TIME));
    }

    @Override
    public void onLoop() {
        //Hardware.log("gyro heading", Hardware.instance.gyroWrapper.getHeading());
    }
}