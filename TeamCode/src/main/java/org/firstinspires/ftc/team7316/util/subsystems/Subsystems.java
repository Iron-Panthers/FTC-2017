package org.firstinspires.ftc.team7316.util.subsystems;

import org.firstinspires.ftc.team7316.util.Scheduler;

/**
 * Every subsystem that needs to be used is placed in here.
 */

public class Subsystems {

    public static Subsystems instance = null;

    public MecanumDriveBase driveBase;
    public GlyphIntake glyphIntake;
    public JewelArm jewelArm;
    public RelicArm relicArm;

    public Subsystem[] subsystems;

    private Subsystems () {
        driveBase = new MecanumDriveBase();

        glyphIntake = new GlyphIntake();

        jewelArm = new JewelArm();

        relicArm = new RelicArm();

        subsystems = new Subsystem[]{driveBase, glyphIntake, jewelArm, relicArm};
    }

    public static void createSubsystems() {
        instance = new Subsystems();

        Scheduler.instance.addDefaultCommands();

        instance.resetSubsystems();
    }

    public void resetSubsystems() {
        for(Subsystem s : subsystems) {
            s.reset();
        }
    }
}