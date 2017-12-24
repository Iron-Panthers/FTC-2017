package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistance;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroPID;
import org.firstinspires.ftc.team7316.util.sensors.ColorWrapper;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 10/28/17.
 */

public class WackJewel extends Command {

    private Alliance alliance;
    private Command drivecommand;
    private Command turncommand;
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
            turncommand = new BlankCommand(Subsystems.instance.driveBase, true);
        }
        else {
            if (alliance.shouldHitForward(colorWrapper.sumR(), colorWrapper.sumB())) {
//                drivecommand = new DriveDistance(Constants.JEWEL_WHACK_DISTANCE);
//                colorWrapper.drivenForward = true;

                turncommand = new TurnGyroPID(-Constants.JEWEL_WACK_DELTA, 3);
            } else {
//                drivecommand = new DriveDistance(-Constants.JEWEL_WHACK_DISTANCE);
//                colorWrapper.drivenForward = false;

                turncommand = new TurnGyroPID(Constants.JEWEL_WACK_DELTA, 3);
            }
//            drivecommand.init();
            turncommand.init();
        }
    }

    @Override
    public void loop() {
        Hardware.log("whacking jewel", "yes");
//        Hardware.log("flError", Hardware.instance.frontLeftDriveMotorWrapper.getError());
//        Hardware.log("frError", Hardware.instance.frontRightDriveMotorWrapper.getError());
//        drivecommand.loop();
        turncommand.loop();
    }

    @Override
    public boolean shouldRemove() {
//        return drivecommand.shouldRemove();
        return turncommand.shouldRemove();
    }

    @Override
    protected void end() {
//        drivecommand.interrupt();
        turncommand.interrupt();
    }
}
