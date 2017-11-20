package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Alliance;
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

    private double duration;

    private Alliance alliance;

    private DriveForTime cmd;

    public DriveForTime_PadModified(double duration, Alliance alliance) {
        this.duration = duration;
        this.alliance = alliance;
    }

    @Override
    public void init() {
        //always drive forward
        if(alliance == Alliance.RED) {
            if(Hardware.instance.colorWrapper.noColor) {
                cmd = new DriveForTime(Constants.FORWARD_POWER_FOR_TIME, 0, duration);
            }
            else {
                double power = Hardware.instance.colorWrapper.drivenForward ? Constants.FORWARD_POWER_FOR_TIME : Constants.BACKWARD_POWER_FOR_TIME;
                if (Hardware.instance.colorWrapper.drivenForward) {
                    duration = duration - Constants.TIME_PAD_OFFSET_FORWARD_RED;
                    cmd = new DriveForTime(power, 0, duration);
                } else {
                    duration = duration + Constants.TIME_PAD_OFFSET_BACKWARD_RED;
                    cmd = new DriveForTime(power, 0, duration);
                }
            }
        }
        //always drive backward
        if(alliance == Alliance.BLUE) {
            if(Hardware.instance.colorWrapper.noColor) {
                cmd = new DriveForTime(Constants.FORWARD_POWER_FOR_TIME, Math.PI, duration);
            }
            else {
                double power = Hardware.instance.colorWrapper.drivenForward ? Constants.BACKWARD_POWER_FOR_TIME : Constants.FORWARD_POWER_FOR_TIME;
                if (Hardware.instance.colorWrapper.drivenForward) {
                    duration = duration + Constants.TIME_PAD_OFFSET_BACKWARD_BLUE;
                    cmd = new DriveForTime(power, Math.PI, duration);
                } else {
                    duration = duration - Constants.TIME_PAD_OFFSET_FORWARD_BLUE;
                    cmd = new DriveForTime(power, Math.PI, duration);
                }
            }
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
