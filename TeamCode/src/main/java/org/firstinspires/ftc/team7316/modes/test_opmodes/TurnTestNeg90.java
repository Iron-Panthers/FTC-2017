package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroPID;

/**
 * Created by jerry on 1/12/18.
 */

@Autonomous(name = "TurnTestNeg90")
public class TurnTestNeg90 extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.instance.add(new TurnGyroPID(-90));
    }

    @Override
    public void onLoop() {

    }
}
