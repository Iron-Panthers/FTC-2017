package org.firstinspires.ftc.team7316.util.subsystems;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.BlankCommand;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by jerry on 10/27/17.
 */

public class JewelArm extends Subsystem {

    private ColorSensor sensor;
    private Servo servo;

    public final double servoPositionIn = 0.05;
    public final double servoPositionOut = 1;

    public JewelArm() {
        servo = Hardware.instance.rightJewelArm;
        sensor = Hardware.instance.colorsensor;
    }

    @Override
    public Command defaultAutoCommand() {
        return new BlankCommand(this);
    }

    @Override
    public Command defaultTeleopCommand() {
        return new BlankCommand(this);
    }

    public void moveServo(JewelArmPosition position) {
        if(position == JewelArmPosition.IN) {
            servo.setPosition(servoPositionIn);
        }
        else {
            servo.setPosition(servoPositionOut);
        }
    }

    public enum JewelArmPosition {
        IN,
        OUT
    }
}
