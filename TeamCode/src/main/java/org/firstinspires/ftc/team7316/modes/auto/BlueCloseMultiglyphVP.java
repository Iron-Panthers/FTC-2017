package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;

/**
 * Created by jerry on 3/6/18.
 */

@Autonomous(name = "blue close multiglyph vp", group = "game autos")
public class BlueCloseMultiglyphVP extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Hardware.instance.vuforiaCameraWrapper.startTracking();
        Scheduler.instance.add(AutoCodes.blueCloseMultiglyphVP());
    }

    @Override
    public void onLoop() {

    }
}
