package org.firstinspires.ftc.team7316.util.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.BlankCommand;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.commands.relicarm.RelicArmJoystick;

/**
 * Created by jerry on 10/25/17.
 */

public class RelicArm extends Subsystem {

    //private Servo relicGrabberServo = Hardware.instance.relicGrabberServo;
    //private CRServo relicArmServo = Hardware.instance.relicArmServo;

    @Override
    public Command defaultAutoCommand() {
        return new BlankCommand(this);
    }

    @Override
    public Command defaultTeleopCommand() {
        return new RelicArmJoystick();
    }

    public void stopRelicArm(){
        /*relicArmServo.setPower(0);*/
    }
}
