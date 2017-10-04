package org.firstinspires.ftc.team7316.modes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.modes.BaseOpMode;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.hardware.VuforiaCameraWrapper;

/**
 * Created by jerry on 10/4/17.
 */

@TeleOp(name = "VuforiaTest")
public class VuforiaTestMode extends BaseOpMode {
    VuforiaCameraWrapper camera;
    @Override
    public void onInit() {
        VuforiaCameraWrapper camera = new VuforiaCameraWrapper();
        Scheduler.instance.addTask(camera);
    }

    @Override
    public void onLoop() {

    }
}
