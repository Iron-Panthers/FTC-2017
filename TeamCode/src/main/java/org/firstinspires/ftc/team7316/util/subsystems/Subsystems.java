package org.firstinspires.ftc.team7316.util.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 10/18/17.
 */

public class Subsystems {

    public static Subsystems instance = null;

    public MecanumDriveBase driveBase;

    public Subsystems () {

        driveBase = new MecanumDriveBase();
        Scheduler.instance.addTask(driveBase);
    }

    public static void createSubsystems() {
        instance = new Subsystems();
    }

}
