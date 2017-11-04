package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveDistance;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

import java.util.ArrayList;

/**
 * Created by jerry on 10/28/17.
 */

public class WackJewel extends Command {

    private Alliance alliance;
    private DriveDistance drivecommand;
    private AggregrateColorSensor colorcommand;

    public WackJewel(Alliance alliance) {
        requires(Subsystems.instance.jewelArm);
        this.alliance = alliance;
    }

    @Override
    public void init() {
        colorcommand = new AggregrateColorSensor(Hardware.instance.colorsensor);
        colorcommand.init();
        while (!colorcommand.shouldRemove()) {
            colorcommand.loop();
        }

        if(alliance.shouldHitForward(colorcommand.sumR(), colorcommand.sumB())) {
            drivecommand = new DriveDistance(3, 0.5);
        }
        else {
            drivecommand = new DriveDistance(-3, 0.5);
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
