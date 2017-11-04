package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.BaseOpMode;
import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;

/**
 * Created by jerry on 11/3/17.
 */

@Autonomous(name = "drivedistancetest")
public class DriveDistanceTest extends BaseOpMode {
    @Override
    public void onInit() {
        Scheduler.inTeleop = false;
        Scheduler.instance.add(AutoCodes.driveStraight(10, 1));
    }

    @Override
    public void onLoop() {

    }
}
