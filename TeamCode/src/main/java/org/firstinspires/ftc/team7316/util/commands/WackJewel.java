package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveForTime;
import org.firstinspires.ftc.team7316.util.commands.flow.SequentialCommand;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

import java.util.ArrayList;

/**
 * Created by jerry on 10/28/17.
 */

public class WackJewel extends Command {

    private Alliance alliance;
    private DriveForTime drivecommand;

    public WackJewel(Alliance alliance) {
        requires(Subsystems.instance.jewelArm);
        this.alliance = alliance;
    }

    @Override
    public void init() {
        if(Subsystems.instance.jewelArm.hitFrontJewel(alliance)) {
            drivecommand = new DriveForTime(0.3, 0, 0.1);
        }
        else {
            drivecommand = new DriveForTime(0.3, Math.PI, 0.1);
        }
        drivecommand.init();
    }

    @Override
    public void loop() {
        drivecommand.loop();
    }

    @Override
    public boolean shouldRemove() {
        return drivecommand.shouldRemove();
    }

    @Override
    protected void end() {
        drivecommand.interrupt();
    }
}
