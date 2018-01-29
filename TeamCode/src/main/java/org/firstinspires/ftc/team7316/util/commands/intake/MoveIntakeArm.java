package org.firstinspires.ftc.team7316.util.commands.intake;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 11/18/17. sawn by chris
 */

public class MoveIntakeArm extends Command {

    private double position;

    public MoveIntakeArm(double position) {
        requires(Subsystems.instance.glyphIntake);
        this.position = position;
    }

    @Override
    public void init() {
        Subsystems.instance.glyphIntake.setServoPosition(position);
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {

    }
}
