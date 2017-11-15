package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 10/28/17.
 */

@Autonomous(name = "blueteam wackjewel")
public class BlueWackJewel extends AutoBaseOpMode {

    @Override
    public void onInit() {
        Scheduler.instance.add(AutoCodes.jewelWack(Alliance.BLUE));
    }

    @Override
    public void onLoop() {
        Hardware.log("R", Hardware.instance.colorsensor.red());
        Hardware.log("G", Hardware.instance.colorsensor.green());
        Hardware.log("B", Hardware.instance.colorsensor.blue());
    }
}