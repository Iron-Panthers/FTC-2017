package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.CryptoLocations;
import org.firstinspires.ftc.team7316.util.Hardware;

/**
 * Created by jerry on 10/4/17.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;

/**
 * Created by jerry on 10/4/17.
 */

@Autonomous
public class VuforiaTestMode extends AutoBaseOpMode {

    private RelicRecoveryVuMark currentPic = RelicRecoveryVuMark.UNKNOWN;

    @Override
    public void onInit() {
        CryptoLocations.setConfig(currentPic, 0);
        Hardware.instance.vuforiaCameraWrapper.startTracking();
    }

    @Override
    public void onLoop() {
        Hardware.instance.vuforiaCameraWrapper.update();
        if(Hardware.instance.vuforiaCameraWrapper.vuMark != currentPic) {
            CryptoLocations.setConfig(Hardware.instance.vuforiaCameraWrapper.vuMark, 0);
        }
    }
}
