package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 10/28/17.
 */

// right now don't strafe please
public class DriveDistance extends Command {

    private int distance; //currently in ticks
    private double angle;
    private double power;


    //parameter is in inches
    public DriveDistance(double distance, double angle, double power) {
        requires(Subsystems.instance.driveBase);
        this.distance = (int)(Constants.ENCODER_TICK_PER_REV * distance / Constants.WHEEL_CIRCUMFERENCE);
        this.power = power;
        this.angle = angle;
    }

    @Override
    public void init() {
        Subsystems.instance.driveBase.stopMotors();
        Subsystems.instance.driveBase.setMotorTargets(distance);
    }

    @Override
    public void loop() {
        Subsystems.instance.driveBase.driveWithSpeedsPID(power);
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
