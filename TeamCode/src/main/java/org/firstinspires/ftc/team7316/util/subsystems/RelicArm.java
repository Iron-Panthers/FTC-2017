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

    private RelicArmJoystick relicArmJoystick;

    private DcMotor relicArmMotor = Hardware.instance.relicArmMotor;
    private CRServo relicShoulderServo = Hardware.instance.relicShoulderServo;
//    private Servo relicWristServo = Hardware.instance.relicWristServo;
    private Servo relicHandServoLeft = Hardware.instance.relicHandServoLeft;
    private Servo relicHandServoRight = Hardware.instance.relicHandServoRight;

    public double clawServoTarget = 0.8;

    @Override
    public Command defaultAutoCommand() {
        return null;
    }

    @Override
    public Command defaultTeleopCommand() {
        if (relicArmJoystick == null) {
            relicArmJoystick = new RelicArmJoystick();
        }
        return relicArmJoystick;
    }

    @Override
    public void reset() {
        setArmPower(0);
        closeClaw();
    }

    public void setArmPower(double power) {
        relicArmMotor.setPower(power);
    }

    public void extendShoulder() {
        relicShoulderServo.getController().setServoPosition(relicShoulderServo.getPortNumber(), 1);
//        relicWristServo.setPosition(0);
    }

    public void retractShoulder() {
        relicShoulderServo.getController().setServoPosition(relicShoulderServo.getPortNumber(), 0);
//        relicWristServo.setPosition(0.9);
    }

    public void setClawPosition(double position) {
        relicHandServoLeft.setPosition(position);
        relicHandServoRight.setPosition(position);
    }

    public void openClaw() {
        relicHandServoLeft.setPosition(0.45);
        relicHandServoRight.setPosition(0.45);
    }

    public void closeClaw() {
        relicHandServoLeft.setPosition(0.8);
        relicHandServoRight.setPosition(0.8);
    }
}
