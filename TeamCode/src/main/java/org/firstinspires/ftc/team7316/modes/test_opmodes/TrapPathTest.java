package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistancePath;
import org.firstinspires.ftc.team7316.util.path.CombinedPath;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 12/12/17.
 */
@Autonomous(name = "trapdude")
public class TrapPathTest extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.instance.add(new DriveDistancePath(new CombinedPath.LongitudalTrapezoid(0, Constants.inchesToTicks(10), 1700, 100)));
    }

    @Override
    public void onLoop() {

    }
}
