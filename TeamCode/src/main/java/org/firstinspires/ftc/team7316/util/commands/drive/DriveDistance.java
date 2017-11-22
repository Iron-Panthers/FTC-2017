package org.firstinspires.ftc.team7316.util.commands.drive;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 10/28/17.
 */

public class DriveDistance extends Command {

    private int distance; // in ticks

    private double timeout;
    private ElapsedTime timer;

    private int completedCount;
    private final int countThreshold = 10;

    public DriveDistance(double inches) {
        //requires(Subsystems.instance.driveBase);
        this.distance = (int)Constants.inchesToTicks(inches);
        this.timeout = 100;
        timer = new ElapsedTime();
    }

    /**
     * @param inches desired distance
     * @param timeout the time before the command will stop
     */
    public DriveDistance(double inches, double timeout) {
        //requires(Subsystems.instance.driveBase);
        this.distance = (int)Constants.inchesToTicks(inches);
        this.timeout = timeout;
        timer = new ElapsedTime();
    }

    @Override
    public void init() {
        timer.reset();
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
        return completedCount >= countThreshold || timer.seconds() > timeout;
    }

    @Override
    protected void end() {
        Subsystems.instance.driveBase.stopMotors();
    }
}
