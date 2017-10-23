package org.firstinspires.ftc.team7316.util.subsystems;

import org.firstinspires.ftc.team7316.util.Scheduler;

/**
 * Created by andrew on 10/18/17.
 */

public class Subsystems {

    public static Subsystems instance = null;

    public MecanumDriveBase driveBase;
    public GlyphIntake glyphIntake;

    private Subsystems () {

        driveBase = new MecanumDriveBase();
        Scheduler.instance.add(driveBase.getDefaultCommand());

        glyphIntake = new GlyphIntake();
        Scheduler.instance.add(glyphIntake.getDefaultCommand());

    }

    public static void createSubsystems() {
        instance = new Subsystems();
    }

}
