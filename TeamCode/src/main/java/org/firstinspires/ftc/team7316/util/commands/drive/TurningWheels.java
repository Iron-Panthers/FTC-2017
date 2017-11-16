package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 11/15/17.
 */

public class TurningWheels extends Command {
    @Override
    public void init() {
        requires(Subsystems.instance.driveBase);
    }

    @Override
    public void loop() {
        Hardware.log("backleft position", Hardware.instance.backLeftDriveMotor.getCurrentPosition());
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {

    }
}
