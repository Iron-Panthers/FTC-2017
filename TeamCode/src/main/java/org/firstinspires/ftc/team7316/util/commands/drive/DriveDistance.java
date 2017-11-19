package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 10/28/17.
 */

public class DriveDistance extends Command {

    private int distance; // in ticks

    private int completedCount;
    private final int countThreshold = 10;

    /**
     * @param inches desired distance
     */
    public DriveDistance(double inches) {
        //requires(Subsystems.instance.driveBase);
        this.distance = (int)Constants.inchesToTicks(inches);
    }

    @Override
    public void init() {
        completedCount = 0;
        Subsystems.instance.driveBase.stopMotors();
        Subsystems.instance.driveBase.resetMotorModes();
        Subsystems.instance.driveBase.resetEncoders();
        Subsystems.instance.driveBase.setMotorTargets(distance);
        Subsystems.instance.driveBase.setMotorMaxSpeeds(Constants.STRAIGHT_DRIVE_MAXSPEED);

    }

    @Override
    public void loop() {
        if(Subsystems.instance.driveBase.completedDistance()) {
            completedCount ++;
        }
        Hardware.log("driving distance", "cool and good");
        Hardware.log("flError", Hardware.instance.frontLeftDriveMotorWrapper.getError());
        Hardware.log("frError", Hardware.instance.frontRightDriveMotorWrapper.getError());
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
