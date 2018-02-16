package org.firstinspires.ftc.team7316.util.commands.intake;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 11/10/17.
 */

public class IntakeForTime extends Command {

    private double power;
    private double duration;

    private ElapsedTime timer;

    public IntakeForTime(double power, double duration) {
        requires(Subsystems.instance.glyphIntake);
        this.power = power * -1;
        this.duration = duration;
        timer = new ElapsedTime();
    }

    @Override
    public void init() {
        timer.reset();
        Subsystems.instance.glyphIntake.setIntakePower(0, 0);
    }

    @Override
    public void loop() {
        Subsystems.instance.glyphIntake.setIntakePower(power, power);
    }

    @Override
    public boolean shouldRemove() {
        return timer.seconds() >= duration;
    }

    @Override
    protected void end() {
        Subsystems.instance.glyphIntake.setIntakePower(0, 0);
    }
}
