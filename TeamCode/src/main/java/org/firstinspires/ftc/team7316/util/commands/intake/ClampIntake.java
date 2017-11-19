package org.firstinspires.ftc.team7316.util.commands.intake;

import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 11/18/17.
 */

public class ClampIntake extends Command {

    public ClampIntake() {

    }

    @Override
    public void init() {
        Subsystems.instance.glyphIntake.setServoPosition(0.2);
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
