package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.path.CombinedPath;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * only for driving straight lul
 * Created by jerry on 12/8/17.
 */

public class VPDriveDistance extends Command {

    private int target;

    private int completedCount;
    private final int countThreshold = 10;

    public VPDriveDistance(double millimeters) {
        /*
        need to do something here where it takes the rotation of the cipher and calculates
        distance from that.
         */
        Hardware.instance.vuforiaCameraWrapper.setCipherLocation();
        target = Constants.millimetersToTicks(millimeters);
        completedCount = 0;
    }

    @Override
    public void init() {
        Subsystems.instance.driveBase.resetEncoders();
        CombinedPath.LongitudalTrapezoid path = new CombinedPath.LongitudalTrapezoid(0, target, 1, 0.2);
        Subsystems.instance.driveBase.setMotorPaths(path);
    }

    @Override
    public void loop() {
        if(Subsystems.instance.driveBase.completedDistance()) {
            completedCount++;
        }
        Subsystems.instance.driveBase.driveWithSpeedsPID();
    }

    @Override
    public boolean shouldRemove() {
        return completedCount >= countThreshold;
    }

    @Override
    protected void end() {
        Subsystems.instance.driveBase.stopMotors();
    }
}
