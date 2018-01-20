package org.firstinspires.ftc.team7316.util.subsystems;

import org.firstinspires.ftc.team7316.util.Scheduler;

/**
 * Created by andrew on 10/18/17.
 */

public class Subsystems {

    public static Subsystems instance = null;

    public MecanumDriveBase driveBase;
    public GlyphIntake glyphIntake;
    public JewelArm jewelArm;
    public RelicArm relicArm;

    private Subsystems () {

        driveBase = new MecanumDriveBase();

        glyphIntake = new GlyphIntake();

        jewelArm = new JewelArm();

        relicArm = new RelicArm();
    }

    public static void createSubsystems() {
        instance = new Subsystems();

        Scheduler.instance.add(instance.driveBase.getDefaultCommand());
        Scheduler.instance.add(instance.glyphIntake.getDefaultCommand());
        Scheduler.instance.add(instance.jewelArm.getDefaultCommand());
        Scheduler.instance.add(instance.relicArm.getDefaultCommand());
    }

}