package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistance;
import org.firstinspires.ftc.team7316.util.commands.flow.SimultaneousKeyCommand;
import org.firstinspires.ftc.team7316.util.commands.intake.RunIntake;

/**
 * Created by jerry on 2/8/18.
 */

@Autonomous(name = "simul key test")
public class SimultaneousKeyTest extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.instance.add(new SimultaneousKeyCommand(new DriveDistance(10), new RunIntake(-0.7)));
    }

    @Override
    public void onLoop() {
        Hardware.log("fatty", "fatty");
    }
}
