package org.firstinspires.ftc.team7316.util.commands.relicarm;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Hardware;
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
        if(OI.instance.gp2.dp_left.state()) {
            relicArm.setArmPower(0.3);
        }
        else if(OI.instance.gp2.dp_right.state()) {
            relicArm.setArmPower(-0.3);
        }
        else {
            relicArm.setArmPower(0);
        }

        if(OI.instance.gp2.a_button.state()) {
            relicArm.openClaw();
            Hardware.log("claw", "opened");
        }
        else {
            relicArm.closeClaw();
            Hardware.log("claw", "closed");
        }

        if(OI.instance.gp2.b_button.state()) {
            relicArm.extendShoulder();
            Hardware.log("shoulder", "extended");
        }
        else {
            relicArm.retractShoulder();
            Hardware.log("shoulder", "retracted");
        }
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
