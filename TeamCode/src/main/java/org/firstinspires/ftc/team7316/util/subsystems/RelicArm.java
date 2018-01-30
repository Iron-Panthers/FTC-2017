package org.firstinspires.ftc.team7316.util.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.commands.relicarm.RelicArmJoystick;

/**
 * Created by jerry on 10/25/17.
 */

public class RelicArm extends Subsystem {

    private RelicArmJoystick relicArmJoystick = new RelicArmJoystick();

    private DcMotor relicArmMotor = Hardware.instance.relicArmMotor;
    private CRServo relicShoulderServo = Hardware.instance.relicShoulderServo;
    private Servo relicWristServo = Hardware.instance.relicWristServo;
    private Servo relicHandServo = Hardware.instance.relicHandServo;

    @Override
    public Command defaultAutoCommand() {
        return null;
    }

    @Override
    public Command defaultTeleopCommand() {
        return relicArmJoystick;
    }

    public void setArmPower(double power) {
        relicArmMotor.setPower(power);
    }

    public void extendShoulder() {
        relicShoulderServo.getController().setServoPosition(relicShoulderServo.getPortNumber(), 1);
        relicWristServo.setPosition(0);
    }

    public void retractShoulder() {
        relicShoulderServo.getController().setServoPosition(relicShoulderServo.getPortNumber(), 0);
        relicWristServo.setPosition(0.9);
    }

    public void openClaw() {
        relicHandServo.setPosition(0.5);
    }

    public void closeClaw() {
        relicHandServo.setPosition(0.6);
    }
}
