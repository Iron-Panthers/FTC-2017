package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.modes.TeleopBaseOpMode;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.intake.IntakeServoTest;

/**
 * Created by jerry on 12/30/17.
 */

public class IntakeServoTesting extends TeleopBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.instance.add(new IntakeServoTest());
    }

    @Override
    public void onLoop() {

    }
}
