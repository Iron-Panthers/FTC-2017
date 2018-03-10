package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistance;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 3/10/18.
 */

public class RunUntilIntake extends Command {

    private int count;
    private boolean prevTouch;

    private int maxTicks;
    private DriveDistance cmd;

    public RunUntilIntake(int maxTicks) {
        this.maxTicks = maxTicks;
    }

    @Override
    public void init() {
        count = 0;
        cmd = new DriveDistance(maxTicks, 4);
        cmd.init();
    }

    @Override
    public void loop() {
        boolean state = Hardware.instance.glyphTouchSensor.isPressed();

        if(!prevTouch && state) {
            count++;
        }

        Subsystems.instance.glyphIntake.setIntakePower(-0.7, -0.7);
        cmd.loop();
        prevTouch = state;
    }

    @Override
    public boolean shouldRemove() {
        return count >= 2 || cmd.shouldRemove();
    }

    @Override
    protected void end() {
        Subsystems.instance.glyphIntake.setIntakePower(0, 0);
        cmd.interrupt();
    }
}
