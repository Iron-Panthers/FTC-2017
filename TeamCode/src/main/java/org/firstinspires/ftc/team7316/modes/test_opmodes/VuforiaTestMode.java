package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.CryptoLocations;
import org.firstinspires.ftc.team7316.util.Hardware;

/**
 * Created by jerry on 10/4/17.
 */

/**
 * Created by jerry on 10/4/17.
 */

@Autonomous
public class VuforiaTestMode extends AutoBaseOpMode {

    @Override
    public void onInit() {
        Hardware.instance.vuforiaCameraWrapper.startTracking();
    }

    @Override
    public void onLoop() {
        Hardware.instance.vuforiaCameraWrapper.update();
        if(Hardware.instance.vuforiaCameraWrapper.vuMark != RelicRecoveryVuMark.UNKNOWN) {
            CryptoLocations.setConfig(Hardware.instance.vuforiaCameraWrapper.vuMark, CryptoLocations.CLOSE_RED_AUTO);

            double amount = CryptoLocations.angleForBox(Hardware.instance.vuforiaCameraWrapper.irY, Hardware.instance.vuforiaCameraWrapper.itZ, Hardware.instance.vuforiaCameraWrapper.itY);
            double distance = CryptoLocations.distanceForBox(Hardware.instance.vuforiaCameraWrapper.irY, Hardware.instance.vuforiaCameraWrapper.itZ, Hardware.instance.vuforiaCameraWrapper.itY);
            Hardware.log("target angle", amount);
            Hardware.log("target distance", distance);
        }
    }
}
