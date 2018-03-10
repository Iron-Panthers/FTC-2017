package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.modes.TeleopBaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.Util;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.commands.drive.StrafeTurn;
import org.firstinspires.ftc.team7316.util.input.OI;

/**
 * Created by jerry on 3/10/18.
 */

@TeleOp
public class StrafeTurnTesting extends TeleopBaseOpMode {

    private Command currentCmd;
    private boolean running;

    @Override
    public void onInit() {

    }

    @Override
    public void onLoop() {
        if(OI.instance.gp1.a_button.state() && !running) {
            currentCmd = new StrafeTurn(Util.wrap(Hardware.instance.gyroWrapper.getHeading() + 180), 0.3, 0.7);
            Scheduler.instance.add(currentCmd);
            running = true;
        }
        if(currentCmd != null && currentCmd.shouldRemove()) {
            running = false;
            currentCmd = null;
        }
    }
}
