package org.firstinspires.ftc.team7316.util.commands;

/**
 * Created by andrew on 10/23/17.
 */

import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.input.OI;
import org.firstinspires.ftc.team7316.util.subsystems.GlyphIntake;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

public class GlyphIntakeJoystick extends Command {

    private GlyphIntake intake = Subsystems.instance.glyphIntake;

    public GlyphIntakeJoystick() {
        requires(Subsystems.instance.glyphIntake);
    }

    @Override
    public void init() {
        this.intake.setPosition(0);
    }

    @Override
    public void loop() {
        this.intake.setIntakePower(OI.instance.gp2.left_stick.getY());
        this.intake.setPosition(OI.instance.gp2.right_stick.getX());
        if(OI.instance.gp2.dp_up.state()) {
            this.intake.setLiftPower(true);
        }
        else if(OI.instance.gp2.dp_down.state()) {
            this.intake.setLiftPower(false);
        }
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void end() {
        this.intake.setIntakePower(0);
    }

}
