package org.firstinspires.ftc.team7316.util.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.BlankCommand;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.commands.relicarm.RelicArmJoystick;

/**
 * Created by jerry on 10/25/17.
 */

public class RelicArm extends Subsystem {

    private DcMotor relicArmMotor = Hardware.instance.relicArmMotor;
    private CRServo relicShoulderServo = Hardware.instance.relicShoulderServo;
    private Servo relicWristServo = Hardware.instance.relicWristServo;
    private Servo relicHandServo = Hardware.instance.relicHandServo;

    @Override
    public Command defaultAutoCommand() {
        return new BlankCommand(this);
    }

    @Override
    public Command defaultTeleopCommand() {
        return new RelicArmJoystick();
    }

    public void setArmPower(double power) {
        relicArmMotor.setPower(power);
    }
}
