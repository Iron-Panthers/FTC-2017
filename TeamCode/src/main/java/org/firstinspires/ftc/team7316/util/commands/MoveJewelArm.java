package org.firstinspires.ftc.team7316.util.commands;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.subsystems.JewelArm;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 10/28/17.
 */

public class MoveJewelArm extends Command {

    private ElapsedTime pressedTime;
    private JewelArm.JewelArmPosition direction;

    public MoveJewelArm(JewelArm.JewelArmPosition direction) {
        requires(Subsystems.instance.jewelArm);
        this.direction = direction;
        this.pressedTime = new ElapsedTime();
    }

    @Override
    public void init() {
        pressedTime.reset();
        Subsystems.instance.jewelArm.moveServo(direction);
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return pressedTime.seconds() >= Constants.ARM_SERVO_TRAVEL_TIME;
    }

    @Override
    protected void end() {

    }
}
