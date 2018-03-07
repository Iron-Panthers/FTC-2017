package org.firstinspires.ftc.team7316.modes;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.team7316.util.CryptoLocations;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Opmode that does all the stuff needed for auto only
 */

public abstract class AutoBaseOpMode extends OpMode {

    /**
     * Vuforia takes forever to init so DON'T REMOVE THIS
     */
    public AutoBaseOpMode() {
        msStuckDetectInit = 10000;
    }

    @Override
    public void init() {
        Log.i(Hardware.tag, "=========================== STARTING AUTO ================================");
        Scheduler.inTeleop = false;
        CryptoLocations.resetCryptoLocations();
        Scheduler.instance.clear();
        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);
        Subsystems.createSubsystems();
        onInit();
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
        onLoop();
    }

    @Override
    public void stop() {
        Subsystems.instance.resetSubsystems();
    }

    /**
     * Runs after Hardware is initialized
     */
    public abstract void onInit();

    /**
     * Runs every loop after Scheduler loops
     */
    public abstract void onLoop();
}
