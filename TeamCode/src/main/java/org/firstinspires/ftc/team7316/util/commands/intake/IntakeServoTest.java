package org.firstinspires.ftc.team7316.util.commands.intake;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.input.OI;
import org.firstinspires.ftc.team7316.util.subsystems.GlyphIntake;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 12/30/17.
 */

public class IntakeServoTest extends Command {
    private GlyphIntake intake = Subsystems.instance.glyphIntake;
    @Override
    public void init() {

    }

    @Override
    public void loop() {
        if(OI.instance.gp2.right_bumper.state()) {
            intake.setServoPosition(1);
            Hardware.log("not using joystick", "cool");
        }
        else if(OI.instance.gp2.left_bumper.state()) {
            intake.setServoPosition(0);
            Hardware.log("not using joystick", "cool");
        }
        else if(OI.instance.gp2.right_stick.getX() >= 0){
            intake.setServoPosition(OI.instance.gp2.right_stick.getX());
            Hardware.log("using joystick", "cool");
        }
        Hardware.log("joystick x", OI.instance.gp2.right_stick.getX());
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {

    }
}
