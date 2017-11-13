package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team7316.modes.BaseOpMode;
import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;

/**
 * Created by jerry on 10/28/17.
 */

@Autonomous(name = "blueteam wackjewel")
public class BlueWackJewel extends BaseOpMode {

    @Override
    public void onInit() {
        Scheduler.inTeleop = false;
        Scheduler.instance.add(AutoCodes.jewelWack(Alliance.BLUE));
    }

    @Override
    public void onLoop() {
    }
}