package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 10/28/17.
 */

// right now don't strafe please
public class DriveDistance extends Command {

    private int distance; //currently in ticks
    private double power;


    //distance is in inches
    public DriveDistance(double distance, double power) {
        requires(Subsystems.instance.driveBase);
        this.distance = (int)(Constants.ENCODER_TICK_PER_REV * distance / Constants.WHEEL_CIRCUMFERENCE);
        this.power = power;
    }

    @Override
    public void init() {
        Subsystems.instance.driveBase.stopMotors();
        Subsystems.instance.driveBase.setMotorTargets(distance);
        Subsystems.instance.driveBase.setMotorModeDistance();
    }

    @Override
    public void loop() {
        Subsystems.instance.driveBase.runMotorsDistance(power);
        Hardware.log("flpower", Hardware.instance.frontLeftDriveMotor.getPower());
        Hardware.log("frpower", Hardware.instance.frontRightDriveMotor.getPower());
        Hardware.log("blpower", Hardware.instance.backLeftDriveMotor.getPower());
        Hardware.log("brpower", Hardware.instance.backRightDriveMotor.getPower());
    }

    @Override
    public boolean shouldRemove() {
        return Subsystems.instance.driveBase.completedDistance();
    }

    @Override
    protected void end() {
        Subsystems.instance.driveBase.stopMotors();
        Subsystems.instance.driveBase.resetMotorModes();
    }
}
