package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.StraightTurn;

/**
 * Created by jerry on 1/28/18.
 */

public class StraightTurnTest extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.instance.add(new StraightTurn(180, 0.6, 0.8));
    }

    @Override
    public void onLoop() {

    }
}
