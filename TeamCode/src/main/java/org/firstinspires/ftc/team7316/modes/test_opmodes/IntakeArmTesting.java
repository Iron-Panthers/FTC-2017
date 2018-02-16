package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.flow.SequentialCommand;
import org.firstinspires.ftc.team7316.util.commands.flow.Wait;
import org.firstinspires.ftc.team7316.util.commands.intake.MoveIntakeArm;

/**
 * Created by jerry on 2/15/18.
 */

@Autonomous
public class IntakeArmTesting extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.instance.add(new SequentialCommand(new MoveIntakeArm(Constants.INTAKE_CLAMP_GLYPH_POSITION), new Wait(3), new MoveIntakeArm(0.8)));
    }

    @Override
    public void onLoop() {

    }
}
