package org.firstinspires.ftc.team7316.modes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.modes.TelopBaseOpMode;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.TurningWheels;

/**
 * Created by jerry on 11/15/17.
 */

@TeleOp(name = "turning wheelll")
public class WheelTurningJuice extends TelopBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.instance.add(new TurningWheels());
    }

    @Override
    public void onLoop() {

    }
}
