package org.firstinspires.ftc.team7316.util.commands.drive.turn;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 11/19/17.
 */

public class TurnForTime extends Command {

    private double rotations;
    private double duration;

    private ElapsedTime timer;

    public TurnForTime(double rotations) {
        requires(Subsystems.instance.driveBase);
        this.rotations = rotations;
        duration = this.rotations * Constants.ROTATIONS_PER_SECOND;
        timer = new ElapsedTime();
    }

    @Override
    public void init() {
        timer.reset();
        Subsystems.instance.driveBase.stopMotors();
    }

    @Override
    public void loop() {
        Subsystems.instance.driveBase.turnMotors(1);
    }

    @Override
    public boolean shouldRemove() {
        return timer.seconds() >= duration;
    }

    @Override
    protected void end() {
        Subsystems.instance.driveBase.stopMotors();
    }
}
