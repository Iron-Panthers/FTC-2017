package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 10/28/17.
 */

public class DriveDistance extends Command {

    private int distance; // in ticks
    private double power;

    /**
     * @param distance desired distance in inches
     */
    public DriveDistance(double distance) {
        //requires(Subsystems.instance.driveBase);
        this.distance = (int)(Constants.ENCODER_TICK_PER_REV / Constants.ENCODER_REV_PER_WHEEL_REV * distance / Constants.WHEEL_CIRCUMFERENCE);
        this.distance = (int)Constants.distanceToTicks(distance);
    }

    @Override
    public void init() {
        Subsystems.instance.driveBase.stopMotors();
        Subsystems.instance.driveBase.resetMotorModes();
        //Subsystems.instance.driveBase.resetEncoders();
        Subsystems.instance.driveBase.setMotorTargets(distance);
        Subsystems.instance.driveBase.setMotorMaxSpeeds(Constants.STRAIGHT_DRIVE_MAXSPEED);

    }

    @Override
    public void loop() {
        System.out.println("c===============================================driving");
        Hardware.log("driving distance", "cool and good");
        Hardware.log("flError", Hardware.instance.frontLeftDriveMotorWrapper.getError());
        Hardware.log("frError", Hardware.instance.frontRightDriveMotorWrapper.getError());
        Subsystems.instance.driveBase.driveWithSpeedsPID();
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
