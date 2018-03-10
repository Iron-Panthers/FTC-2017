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

        //  corresponds with relic arm commands, should probably be made more clear, but oh well
        //  remaps the joysticks to the relic arm
        if(!OI.instance.gp2.leftTriggerWrapper.state()) {
            if (OI.instance.gp2.left_stick.getY() > 0) {
                this.intake.setIntakePower(OI.instance.gp2.left_stick.getY(), OI.instance.gp2.left_stick.getY()); //faster outtaking
            } else {
                this.intake.setIntakePower(OI.instance.gp2.left_stick.getY() * Constants.INTAKE_LEFT_POWER_WEIGHTING, OI.instance.gp2.left_stick.getY() * Constants.INTAKE_RIGHT_POWER_WEIGHTING);
            }

            //  the intake clamp button
            if(OI.instance.gp2.right_bumper.state()) {
                Hardware.log("ist good", "ya");
                this.intake.setServoPosition(Constants.INTAKE_CLAMP_GLYPH_POSITION);
            }
            else if(OI.instance.gp2.rightTriggerWrapper.state()) {
                //  on the off chance the chain skips or something, we need to clamp harder
                this.intake.setServoPosition(Constants.INTAKE_CLAMP_GLYPH_STRONG_POSITION);
            }
            else if(OI.instance.gp2.right_stick.getX() >= 0){
                this.intake.setServoPositionScaled(1 - OI.instance.gp2.right_stick.getX());
            }
        }
        else {
            //  stop intake motors when trying to use relic arm
            this.intake.setIntakePower(0, 0);

            //  the intake clamp button
            if(OI.instance.gp2.right_bumper.state()) {
                this.intake.setServoPosition(Constants.INTAKE_CLAMP_GLYPH_POSITION);
            }
        }

        //  the lift button mappings
        if(OI.instance.gp2.dp_up.state()) {
            this.intake.setLiftPower(-Constants.INTAKE_LIFT_POWER);
        }
        else if(OI.instance.gp2.dp_down.state()) {
            this.intake.setLiftPower(Constants.INTAKE_LIFT_POWER);
        }
        else {
            this.intake.setLiftPower(0);
        }

        //  flag servo stuff
        if(Hardware.instance.glyphTouchSensor.isPressed()) {
            Hardware.instance.flagServo.setPosition(0.5);
        }
        else {
            Hardware.instance.flagServo.setPosition(1);
        }
        Hardware.log("butt pressed", Hardware.instance.glyphTouchSensor.isPressed());
        Hardware.log("lift position", Hardware.instance.intakeLiftMotor.getCurrentPosition());


//------------------testing servos------------------------//
//        if(OI.instance.gp2.right_bumper.state()) {
//            intake.setServoPosition(1);
//            Hardware.log("not using joystick", "cool");
//        }
//        else if(OI.instance.gp2.left_bumper.state()) {
//            intake.setServoPosition(0);
//            Hardware.log("not using joystick", "cool");
//        }
        //0 is inwards, 1 is outwards (i think)
//        else if(OI.instance.gp2.right_stick.getX() >= 0){
//            intake.setServoPosition(OI.instance.gp2.right_stick.getX());
//            Hardware.log("using joystick", "cool");
//        }
//        Hardware.log("joystick x", OI.instance.gp2.right_stick.getX());
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void end() {
        this.intake.setIntakePower(0, 0);
        Hardware.instance.intakeLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

}
