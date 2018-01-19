package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveOffPad;

/**
 * Created by jerry on 1/18/18.
 */

@Autonomous(name = "pahase 1 test")
public class Phase1Test extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.instance.add(new DriveOffPad(Alliance.BLUE));
    }

    @Override
    public void onLoop() {

    }
}
