package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnUntilKey;

/**
 * Created by jerry on 3/6/18.
 */

@Autonomous
@Disabled
public class TurnUntilTest extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.instance.add(new TurnUntilKey(45, 0));
    }

    @Override
    public void onLoop() {

    }
}
