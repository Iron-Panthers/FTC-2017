package org.firstinspires.ftc.team7316.util.subsystems;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by jerry on 10/27/17.
 */

public class JewelArm extends Subsystem {

    private ColorSensor sensor;
    private Servo arm;
    private Servo wacker;

    public final double armPositionIn = 1;
    public final double armPositionOut = 0;

    public final double wackPositionInit = 0.6;
    public final double wackPositionNeutral = 0.5;  //originally 0.5
    public final double wackPositionForward = 0.2;
    public final double wackPositionBackward = 0.8;

    public JewelArm() {
        arm = Hardware.instance.rightJewelArm;
        wacker = Hardware.instance.wackingJewelArm;
        sensor = Hardware.instance.colorsensor;
    }

    @Override
    public Command defaultAutoCommand() {
        return null;
    }

    @Override
    public Command defaultTeleopCommand() {
        return null;
    }

    @Override
    public void reset() {
        moveArm(JewelArmPosition.IN);
        moveWacker(JewelWackPosition.INITIAL);
    }

    public void moveArm(JewelArmPosition position) {
        if(position == JewelArmPosition.IN) {
            arm.setPosition(armPositionIn);
        }
        else {
            arm.setPosition(armPositionOut);
        }
    }

    public void moveWacker(JewelWackPosition position) {
        switch (position) {
            case FORWARD:
                wacker.setPosition(wackPositionForward);
                break;
            case BACKWARD:
                wacker.setPosition(wackPositionBackward);
                break;
            case NEUTRAL:
                wacker.setPosition(wackPositionNeutral);
                break;
            case INITIAL:
                wacker.setPosition(wackPositionInit);
        }
    }

    public enum JewelArmPosition {
        IN,
        OUT
    }

    public enum JewelWackPosition {
        FORWARD,
        NEUTRAL,
        BACKWARD,
        INITIAL
    }
}
