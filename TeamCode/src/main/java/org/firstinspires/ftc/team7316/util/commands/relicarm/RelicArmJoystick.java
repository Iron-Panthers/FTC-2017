package org.firstinspires.ftc.team7316.util.commands.relicarm;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.input.OI;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 1/3/18.
 */

public class RelicArmJoystick extends Command {
    @Override
    public void init() {
        Hardware.instance.relicArmMotor.setPower(0);
        Hardware.instance.relicArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        if(OI.instance.gp2.dp_left.state()) {
            Subsystems.instance.relicArm.setArmPower(0.3);
        }
        else if(OI.instance.gp2.dp_right.state()) {
            Subsystems.instance.relicArm.setArmPower(-0.3);
        }
        else {
            Subsystems.instance.relicArm.setArmPower(0);
        }
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {
        Hardware.instance.relicArmMotor.setPower(0);
    }
}
