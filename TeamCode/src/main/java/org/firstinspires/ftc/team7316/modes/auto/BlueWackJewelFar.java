package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;

/**
 * Created by jerry on 10/28/17.
 */

@Autonomous(name = "blue team jewel far")
public class BlueWackJewelFar extends AutoBaseOpMode {

    @Override
    public void onInit() {
        Scheduler.instance.add(AutoCodes.farBlueJewel());
    }

    @Override
    public void onLoop() {

    }
}