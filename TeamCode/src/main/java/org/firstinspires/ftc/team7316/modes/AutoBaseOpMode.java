package org.firstinspires.ftc.team7316.modes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Opmode that does all the stuff needed for auto only
 */

public abstract class AutoBaseOpMode extends OpMode {

    /**
     * vuforia takes forever to init so DON'T REMOVE THIS
     */
    public AutoBaseOpMode() {
        msStuckDetectInit = 10000;
    }

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
        Scheduler.instance.loop();
        onLoop();
        Hardware.log("gyro heading", Hardware.instance.gyroWrapper.getHeading());
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
