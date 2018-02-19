package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.commands.sensors.UpdateVuforia;

/**
 * Created by jerry on 2/6/18.
 */

//@Autonomous(name = "red far multiglyph", group = "game autos")
public class RedFarMultiglyph extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Hardware.instance.vuforiaCameraWrapper.startTracking();
        Scheduler.instance.add(AutoCodes.redFarMultiglyph());
        Scheduler.instance.add(new UpdateVuforia());
    }

    @Override
    public void onLoop() {

    }
}
