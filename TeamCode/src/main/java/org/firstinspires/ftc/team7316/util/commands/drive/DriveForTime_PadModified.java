package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by jerry on 11/18/17.
 */

/**
 * this drivefortime is modified by the balancing pad offset after whacking the jewel
 * kind of wraps DriveForTime
 */
public class DriveForTime_PadModified extends Command {

    private double power;
    private double direction;
    private double duration;

    private DriveForTime cmd;

    public DriveForTime_PadModified(double power, double direction, double duration) {
        this.power = power;
        this.direction = direction;
        this.duration = duration;
    }

    @Override
    public void init() {
        if(Hardware.instance.colorWrapper.noColor) {
            duration = Math.abs(duration);
            cmd = new DriveForTime(power, direction, duration);
        }
        if(Hardware.instance.colorWrapper.drivenForward) {
            duration = duration - Constants.TIME_PAD_OFFSET_FORWARD;
            duration = Math.abs(duration);
            cmd = new DriveForTime(power, direction, duration);
        }
        else {
            duration = duration + Constants.TIME_PAD_OFFSET_BACKWARD;
            duration = Math.abs(duration);
            cmd = new DriveForTime(power, direction, duration);
        }
        cmd.init();
    }

    @Override
    public void loop() {
        cmd.loop();

    }

    @Override
    public boolean shouldRemove() {
        return cmd.shouldRemove();
    }

    @Override
    protected void end() {
        cmd.interrupt();
    }
}
