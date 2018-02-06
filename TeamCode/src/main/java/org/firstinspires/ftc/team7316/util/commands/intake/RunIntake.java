package org.firstinspires.ftc.team7316.util.commands.intake;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 2/6/18.
 */

public class RunIntake extends Command {
    private double power;

    public RunIntake(double power) {
        requires(Subsystems.instance.glyphIntake);
        this.power = power * -1;
    }

    @Override
    public void init() {
        Subsystems.instance.glyphIntake.setIntakePower(0);
    }

    @Override
    public void loop() {
        Subsystems.instance.glyphIntake.setIntakePower(power);
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {
        Subsystems.instance.glyphIntake.setIntakePower(0);
    }
}
