package org.firstinspires.ftc.team7316.util.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by jerry on 10/25/17.
 */

public class RelicArm extends Subsystem {

    private Servo relicGrabberServo = Hardware.instance.relicGrabberServo;
    private CRServo relicArmServo = Hardware.instance.relicArmServo;

    @Override
    public Command defaultAutoCommand() {
        return null;
    }

    @Override
    public Command defaultTeleopCommand() {
        return null;
    }

    public void stopRelicArm(){
        relicArmServo.setPower(0);
    }
}
