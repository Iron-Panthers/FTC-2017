package org.firstinspires.ftc.team7316.modes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Opmode that does all the stuff needed for auto only
 */

public abstract class AutoBaseOpMode extends OpMode {
    @Override
    public void init() {
        Scheduler.inTeleop = false;
        Scheduler.instance.clear();
        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);
        Subsystems.createSubsystems();
        onInit();
    }

    @Override
    public void loop() {
//        Hardware.instance.vuforiaCameraWrapper.update();
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
