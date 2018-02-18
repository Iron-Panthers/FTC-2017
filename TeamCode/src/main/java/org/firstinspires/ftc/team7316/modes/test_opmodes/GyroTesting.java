package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;

/**
 * Created by jerry on 12/30/17.
 */

public class GyroTesting extends AutoBaseOpMode {
    @Override
    public void onInit() {

    }

    @Override
    public void onLoop() {
        Hardware.instance.gyroWrapper.logAngles();
    }
}
