package org.firstinspires.ftc.team7316.util.commands;

/**
 * Created by andrew on 10/23/17.
 */

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
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
        this.intake.setServoPosition(0.5);
    }

    @Override
    public void loop() {
        // JANKJANKJANK
        if(OI.instance.gp2.dp_up.state()) {
            if(this.intake.getLiftStopped()) {
                this.intake.setLiftStopped(false);
            }
            this.intake.setLiftPower(-Constants.INTAKE_LIFT_POWER);
        }
        else if(OI.instance.gp2.dp_down.state()) {
            if(this.intake.getLiftStopped()) {
                this.intake.setLiftStopped(false);
            }
            this.intake.setLiftPower(Constants.INTAKE_LIFT_POWER);
        }
        else {
            this.intake.setLiftPower(0);
            // CHANGE LATER
            this.intake.setIntakePower(OI.instance.gp2.left_stick.getY());
            this.intake.setServoPosition(OI.instance.gp2.right_stick.getX());
        }
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void end() {
        this.intake.setIntakePower(0);
        Hardware.instance.intakeLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

}
