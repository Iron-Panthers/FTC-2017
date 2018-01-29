package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistance;
import org.firstinspires.ftc.team7316.util.commands.intake.MoveIntakeArm;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * It's literally a command sequence but it sucks
 */

public class BackupAndRam extends Command {

    public BackupAndRam() {
        requires(Subsystems.instance.glyphIntake);
        requires(Subsystems.instance.driveBase);
    }

    @Override
    public void init() {
        Command[] cmds = {
                new MoveIntakeArm(0.8),
                new DriveForTime(0.5, Math.PI, 0.5),
                new MoveIntakeArm(0.2),
                new DriveForTime(0.5, 0, 0.5),
                new DriveDistance(-Constants.FAR_CRYPTO_APPROACH_RED, 2)
        };
        for(Command c : cmds) {
            c.init();
            while(!c.shouldRemove()) {
                c.loop();
            }
            c.interrupt();
        }
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return true;
    }

    @Override
    protected void end() {
        Subsystems.instance.driveBase.stopMotors();
    }
}
