package org.firstinspires.ftc.team7316.util.commands.intake;

/**
 * Created by andrew on 10/23/17.
 */

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
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
        this.intake.setServoPositionScaled(0);
    }

    @Override
    public void loop() {
        if(OI.instance.gp2.left_stick.getY() > 0) {
            this.intake.setIntakePower(OI.instance.gp2.left_stick.getY()); //faster outtaking
        } else {
            this.intake.setIntakePower(OI.instance.gp2.left_stick.getY() * Constants.INTAKE_POWER_WEIGHTING);
        }

        if(OI.instance.gp2.dp_up.state()) {

//            if(Hardware.instance.intakeLiftMotor.getCurrentPosition() > this.intake.liftUpperLimit) {
//                this.intake.setLiftPower(-Constants.INTAKE_LIFT_POWER);
//            }
//            else {
//                this.intake.setLiftPower(0);
//            }
            this.intake.setLiftPower(-Constants.INTAKE_LIFT_POWER);
        }
        else if(OI.instance.gp2.dp_down.state()) {
            /*
            if(Hardware.instance.intakeLiftMotor.getCurrentPosition() < this.intake.liftLowerLimit) {
                this.intake.setLiftPower(Constants.INTAKE_LIFT_POWER);
            }
            else {
                this.intake.setLiftPower(0);
            }*/
            this.intake.setLiftPower(Constants.INTAKE_LIFT_POWER);
        }
        else {
            this.intake.setLiftPower(0);
        }

        if(OI.instance.gp2.right_bumper.state()) {
            this.intake.setServoPosition(0.8);
        }
        else {
            this.intake.setServoPosition(OI.instance.gp2.right_stick.getX());
        }
        Hardware.log("lift position", Hardware.instance.intakeLiftMotor.getCurrentPosition());
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
