package org.firstinspires.ftc.team7316.modes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * OpMode that initializes Hardware and Scheduler for you. Please use this instead of rewriting the
 * same things over and over again because it's much nicer.
 */
public abstract class BaseOpMode extends OpMode {
    @Override
    public void init() {
        Scheduler.instance.clear();
        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);
        onInit();
    }


    @Override
    public void loop() {
        Scheduler.instance.loop();
        onLoop();
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
