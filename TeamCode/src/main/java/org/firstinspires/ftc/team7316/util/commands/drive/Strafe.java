package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 11/17/17.
 */

public class Strafe extends Command {

    private int distance; //ticks
    private int direction; //-1 for left, 1 for right

    /**
     * @param distance in inches
     * @param direction -1 for left, 1 for right
     */
    public Strafe(double distance, int direction) {
        this.distance = (int)(Constants.ENCODER_TICK_PER_REV / Constants.ENCODER_REV_PER_WHEEL_REV * distance / Constants.WHEEL_CIRCUMFERENCE);
        this.direction = direction;
    }

    @Override
    public void init() {
        requires(Subsystems.instance.driveBase);
        if(direction == -1) {
            Subsystems.instance.driveBase.strafeLeft(distance);
        }
        else {
            Subsystems.instance.driveBase.strafeRight(distance);
        }
    }

    @Override
    public void loop() {
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
