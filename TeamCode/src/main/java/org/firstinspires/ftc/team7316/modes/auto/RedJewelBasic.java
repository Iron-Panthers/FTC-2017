package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;

/**
 * Created by jerry on 11/19/17.
 */

@Autonomous(name = "red jewel basic")
public class RedJewelBasic extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.instance.add(AutoCodes.wackJewelBasic(Alliance.RED));
    }

    @Override
    public void onLoop() {
        Hardware.instance.colorWrapper.logSums();
        Hardware.instance.colorWrapper.logColors();
    }
}
