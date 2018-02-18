package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroPID;
import org.firstinspires.ftc.team7316.util.commands.flow.SequentialCommand;

/**
 * Created by jerry on 1/16/18.
 */

public class Doubleturn extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.instance.add(new SequentialCommand(new TurnGyroPID(90), new TurnGyroPID(0)));
    }

    @Override
    public void onLoop() {

    }
}
