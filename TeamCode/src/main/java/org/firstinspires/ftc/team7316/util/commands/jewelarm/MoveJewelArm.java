package org.firstinspires.ftc.team7316.util.commands.jewelarm;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.JewelArm;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 10/28/17.
 */

public class MoveJewelArm extends Command {

    private ElapsedTime travelTime;
    private JewelArm.JewelArmPosition direction;

    private boolean movedWacker;

    public MoveJewelArm(JewelArm.JewelArmPosition direction) {
        //requires(Subsystems.instance.jewelArm);
        this.direction = direction;
        this.travelTime = new ElapsedTime();

        movedWacker = false;
    }

    @Override
    public void init() {
        travelTime.reset();
        Subsystems.instance.jewelArm.moveArm(direction);
    }

    @Override
    public void loop() {
        //move the wacking arm mid-travel
        if(travelTime.seconds() >= 0.2 && !movedWacker) {
            movedWacker = true;
            Subsystems.instance.jewelArm.moveWacker(JewelArm.JewelWackPosition.NEUTRAL);
        }
    }

    @Override
    public boolean shouldRemove() {
        return travelTime.seconds() >= Constants.ARM_SERVO_TRAVEL_TIME;
    }

    @Override
    protected void end() {

    }
}
