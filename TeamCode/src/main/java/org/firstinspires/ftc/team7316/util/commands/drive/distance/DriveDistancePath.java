package org.firstinspires.ftc.team7316.util.commands.drive.distance;

import org.firstinspires.ftc.team7316.copypastaLib.MotionPath;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 12/12/17.
 */

public class DriveDistancePath extends Command {

    private MotionPath path;

    public DriveDistancePath(MotionPath path) {
        this.path = path;
    }

    @Override
    public void init() {
        Subsystems.instance.driveBase.setMotorPaths(path);
    }

    @Override
    public void loop() {
        Subsystems.instance.driveBase.driveWithPID();
    }

    @Override
    public boolean shouldRemove() {
        return Subsystems.instance.driveBase.completedDistance();
    }

    @Override
    protected void end() {
        Subsystems.instance.driveBase.stopMotors();
    }
}
