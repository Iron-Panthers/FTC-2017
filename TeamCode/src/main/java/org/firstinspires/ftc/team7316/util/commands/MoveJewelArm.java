package org.firstinspires.ftc.team7316.util.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.subsystems.JewelArm;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 10/28/17.
 */

public class MoveJewelArm extends Command {

    private ElapsedTime travelTime;
    private JewelArm.JewelArmPosition direction;

    public MoveJewelArm(JewelArm.JewelArmPosition direction) {
        //requires(Subsystems.instance.jewelArm);
        this.direction = direction;
        this.travelTime = new ElapsedTime();
    }

    @Override
    public void init() {
        travelTime.reset();
        Subsystems.instance.jewelArm.moveServo(direction);
    }

    @Override
    public void loop() {
        System.out.println("c===============================================arm moved");
    }

    @Override
    public boolean shouldRemove() {
        return travelTime.seconds() >= Constants.ARM_SERVO_TRAVEL_TIME;
    }

    @Override
    protected void end() {

    }
}
