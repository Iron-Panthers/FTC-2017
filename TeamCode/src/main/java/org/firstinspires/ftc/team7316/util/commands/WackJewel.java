package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistance;
import org.firstinspires.ftc.team7316.util.sensors.ColorWrapper;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 10/28/17.
 */

public class WackJewel extends Command {

    private Alliance alliance;
    private Command drivecommand;
    private ColorWrapper colorWrapper;

    public WackJewel(Alliance alliance) {
        //requires(Subsystems.instance.jewelArm);
        //requires(Subsystems.instance.driveBase);
        this.alliance = alliance;
        colorWrapper = Hardware.instance.colorWrapper;
    }

    @Override
    public void init() {
        //TODO move drivecomand to a better place
        if(colorWrapper.noColor) {
            Hardware.log("oof", "no color detected");
            drivecommand = new BlankCommand(Subsystems.instance.driveBase, true);
        }
        else {
            if (alliance.shouldHitForward(colorWrapper.sumR(), colorWrapper.sumB())) {
                drivecommand = new DriveDistance(Constants.JEWEL_WHACK_DISTANCE);
                colorWrapper.drivenForward = true;
            } else {
                drivecommand = new DriveDistance(-Constants.JEWEL_WHACK_DISTANCE);
                colorWrapper.drivenForward = false;
            }
            drivecommand.init();
        }
    }

    @Override
    public void loop() {
        Hardware.log("whacking jewel", "yes");
        Hardware.log("flError", Hardware.instance.frontLeftDriveMotorWrapper.getError());
        Hardware.log("frError", Hardware.instance.frontRightDriveMotorWrapper.getError());
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
