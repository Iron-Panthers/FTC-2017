package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.TelopBaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;

/**
 * Created by jerry on 11/10/17.
 */

@Autonomous(name = "drivestraightturntest")
public class DriveStraightTurnTest extends TelopBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.inTeleop = false;
        Scheduler.instance.add(AutoCodes.driveStraightTurn(5, 90, 0.5));
    }

    @Override
    public void onLoop() {
        Hardware.log("gyro heading", Hardware.instance.gyroWrapper.getHeading());
    }
}