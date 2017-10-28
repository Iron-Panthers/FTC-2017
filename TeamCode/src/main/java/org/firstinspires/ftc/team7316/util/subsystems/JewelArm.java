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
    private Servo servo;

    public JewelArm() {
        //sensor = Hardware.instance.colorsensor;
    }

    @Override
    public Command defaultAutoCommand() {
        return null;
    }

    @Override
    public Command defaultTeleopCommand() {
        return null;
    }
}
