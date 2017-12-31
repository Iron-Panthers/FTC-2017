package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroPID;

/**
 * Created by andrew on 12/12/17.
 */

@Autonomous(name = "TurnTest30")
public class TurnTest30 extends AutoBaseOpMode {

    @Override
    public void onInit() {
        Scheduler.instance.add(new TurnGyroPID(30));
    }

    @Override
    public void onLoop() {

    }
}