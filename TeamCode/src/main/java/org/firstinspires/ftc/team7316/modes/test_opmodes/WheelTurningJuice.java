package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.TurningWheels;

/**
 * Created by jerry on 11/15/17.
 */

@Autonomous(name = "turning wheelll")
public class WheelTurningJuice extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.instance.add(new TurningWheels());
    }

    @Override
    public void onLoop() {

    }
}
