package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 11/17/17.
 */

public class Strafe extends Command {

    private int distance; //ticks (negative for left, positive for right

    /**
     * @param distance in inches
     */
    public Strafe(double distance) {
        requires(Subsystems.instance.driveBase);
        this.distance = (int)Constants.inchesToTicks(distance);
    }

    @Override
    public void init() {
        if(distance < 0) {
            Subsystems.instance.driveBase.strafeLeft(distance);
        }
        else {
            Subsystems.instance.driveBase.strafeRight(distance);
        }
    }

    @Override
    public void loop() {
        Hardware.log("strafing", "very nice move man");
        Hardware.log("flError", Hardware.instance.frontLeftDriveMotorWrapper.getError());
        Hardware.log("frError", Hardware.instance.frontRightDriveMotorWrapper.getError());
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
