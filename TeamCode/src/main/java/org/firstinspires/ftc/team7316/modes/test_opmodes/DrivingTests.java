package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Scheduler;

/**
 * Created by jerry on 11/10/17.
 */

@Autonomous(name = "nononononono")
public class DrivingTests extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.inTeleop = false;
        //Scheduler.instance.add(new DriveForTime(Constants.FORWARD_POWER_FOR_TIME, 0, Constants.OFF_PAD_TIME));
    }

    @Override
    public void onLoop() {
        //Hardware.log("gyro heading", Hardware.instance.gyroWrapper.getHeading());
    }
}