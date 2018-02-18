package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveForTime;

/**
 * Created by jerry on 2/10/18.
 */

public class StrafeTestNeg30 extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.instance.add(new DriveForTime(0.7, -Math.PI/6, 1));
    }

    @Override
    public void onLoop() {

    }
}
