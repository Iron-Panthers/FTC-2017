package org.firstinspires.ftc.team7316.util.commands.relicarm;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Util;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.input.OI;
import org.firstinspires.ftc.team7316.util.subsystems.RelicArm;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 1/3/18.
 */

public class RelicArmJoystick extends Command {

    private RelicArm relicArm = Subsystems.instance.relicArm;

    @Override
    public void init() {
        relicArm.setArmPower(0);
        Hardware.instance.relicArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
//        if(OI.instance.gp2.dp_left.state()) {
//            relicArm.setArmPower(0.8);
//        }
//        else if(OI.instance.gp2.dp_right.state()) {
//            relicArm.setArmPower(-0.8);
//        }
//        else {
//            relicArm.setArmPower(0);
//        }

//        if(OI.instance.gp2.a_button.state()) {
//            relicArm.openClaw();
//            Hardware.log("claw", "opened");
//        }
//        else {
//            relicArm.closeClaw();
//            Hardware.log("claw", "closed");
//        }

//        if(OI.instance.gp2.b_button.state()) {
//            relicArm.extendShoulder();
//            Hardware.log("shoulder", "extended");
//        }
//        else {
//            relicArm.retractShoulder();
//            Hardware.log("shoulder", "retracted");
//        }

        //  once the left trigger is pressed it should remap the joysticks (and disable intake buttons)
        if(OI.instance.gp2.leftTriggerWrapper.pressedState()) {
            //  the weak servo has reversed controls, currently modifying numbers based on that
            relicArm.clawServoTarget = Util.clamp(relicArm.clawServoTarget - OI.instance.gp2.right_stick.getX() / 20.0, 0.45, 0.9);
            relicArm.setArmPower(OI.instance.gp2.left_stick.getY());
        }
        else {
            relicArm.setArmPower(0);
        }

        relicArm.setClawPosition(relicArm.clawServoTarget);
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {
        relicArm.setArmPower(0);
    }
}
