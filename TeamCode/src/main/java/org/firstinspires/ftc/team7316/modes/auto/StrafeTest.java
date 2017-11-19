package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.TelopBaseOpMode;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.Strafe;

/**
 * Created by jerry on 11/18/17.
 */

@Autonomous(name = "DONT RUN THIS")
public class StrafeTest extends TelopBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.inTeleop = false;
        Scheduler.instance.add(new Strafe(-10));
    }

    @Override
    public void onLoop() {

    }
}
