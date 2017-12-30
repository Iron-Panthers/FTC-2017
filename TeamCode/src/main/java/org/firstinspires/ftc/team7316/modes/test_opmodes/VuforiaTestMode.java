package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;

/**
 * Created by jerry on 10/4/17.
 */

@Autonomous(name = "VuforiaTest")
public class VuforiaTestMode extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Hardware.instance.vuforiaCameraWrapper.startTracking();
    }

    @Override
    public void onLoop() {
        Hardware.instance.vuforiaCameraWrapper.update();
    }
}
