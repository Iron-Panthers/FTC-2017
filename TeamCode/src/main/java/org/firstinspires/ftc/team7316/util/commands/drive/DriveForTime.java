package org.firstinspires.ftc.team7316.util.commands.drive;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 10/28/17.
 */

public class DriveForTime extends Command {

    private double power;
    private double direction;
    private double duration;
    private ElapsedTime time;

    public DriveForTime(double power, double direction, double duration) {
        //requires(Subsystems.instance.driveBase);
        this.power = power;
        this.direction = direction;
        this.duration = duration;
        this.time = new ElapsedTime();
    }

    @Override
    public void init() {
        time.reset();
        Subsystems.instance.driveBase.setWantedSpeedAndMovementAngle(power, direction);
        Subsystems.instance.driveBase.setWantedTurnSpeed(0);
    }

    @Override
    public void loop() {
        Subsystems.instance.driveBase.driveWithSpeeds();
    }

    @Override
    public boolean shouldRemove() {
        return time.seconds() >= duration;
    }

    @Override
    protected void end() {
        Subsystems.instance.driveBase.stopMotors();
    }
}
