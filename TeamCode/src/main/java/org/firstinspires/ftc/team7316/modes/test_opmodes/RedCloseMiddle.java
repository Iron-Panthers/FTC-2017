package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.commands.sensors.UpdateVuforia;

/**
 * Created by jerry on 12/30/17.
 */

@Autonomous(name = "redclosemiddle")
public class RedCloseMiddle extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Hardware.instance.vuforiaCameraWrapper.vuMark = RelicRecoveryVuMark.CENTER;
        Hardware.instance.vuforiaCameraWrapper.startTracking();
        Scheduler.instance.add(AutoCodes.closeBlueJewel());
        Scheduler.instance.add(new UpdateVuforia());
    }

    @Override
    public void onLoop() {

    }
}