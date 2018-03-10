package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistance;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 3/10/18.
 */

public class RunUntilIntake extends Command {

    private int maxTicks;
    private DriveDistance cmd;

    public RunUntilIntake(int maxTicks) {
        this.maxTicks = maxTicks;
        cmd = new DriveDistance(maxTicks, 4);
    }

    @Override
    public void init() {
        cmd.init();
    }

    @Override
    public void loop() {
        Subsystems.instance.glyphIntake.setIntakePower(-0.7, -0.7);
        cmd.loop();
    }

    @Override
    public boolean shouldRemove() {
        return Hardware.instance.glyphTouchSensor.isPressed() || cmd.shouldRemove();
    }

    @Override
    protected void end() {
        Subsystems.instance.glyphIntake.setIntakePower(0, 0);
        cmd.interrupt();
    }
}
