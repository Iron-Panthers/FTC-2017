package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.TelopBaseOpMode;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;

/**
 * Created by jerry on 11/3/17.
 */

@Autonomous(name = "drivedistancetest")
public class DriveDistanceTest extends TelopBaseOpMode {

    @Override
    public void onInit() {
        Scheduler.inTeleop = false;
        Scheduler.instance.add(AutoCodes.driveStraight(10, 0.4));
    }

    @Override
    public void onLoop() {

    }
}
