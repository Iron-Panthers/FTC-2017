package org.firstinspires.ftc.team7316.util.commands.jewelarm;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistance;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroPID;
import org.firstinspires.ftc.team7316.util.sensors.ColorWrapper;
import org.firstinspires.ftc.team7316.util.subsystems.JewelArm;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 10/28/17.
 */

public class WackJewel extends Command {

    private Alliance alliance;
    private ElapsedTime timer;
    private double servoTravelTime = 0.8;
//    private Command drivecommand;
//    private Command turncommand;
    private ColorWrapper colorWrapper;

    public WackJewel(Alliance alliance) {
        requires(Subsystems.instance.jewelArm);
        this.alliance = alliance;
        timer = new ElapsedTime();
        colorWrapper = Hardware.instance.colorWrapper;
    }

    @Override
    public void init() {
        timer.reset();
        if(colorWrapper.noColor) {
            Hardware.log("oof", "no color detected");
//            turncommand = new BlankCommand(Subsystems.instance.driveBase, true);
        }
        else {
            if (alliance.shouldHitForward(colorWrapper.sumR(), colorWrapper.sumB())) {
//                drivecommand = new DriveDistance(Constants.JEWEL_WHACK_DISTANCE);
//                colorWrapper.drivenForward = true;

//                turncommand = new TurnGyroPID(-Constants.JEWEL_WACK_DELTA, 3);
                Subsystems.instance.jewelArm.moveWacker(JewelArm.JewelWackPosition.FORWARD);

            } else {
//                drivecommand = new DriveDistance(-Constants.JEWEL_WHACK_DISTANCE);
//                colorWrapper.drivenForward = false;

//                turncommand = new TurnGyroPID(Constants.JEWEL_WACK_DELTA, 3);
                Subsystems.instance.jewelArm.moveWacker(JewelArm.JewelWackPosition.BACKWARD);
            }
//            drivecommand.init();
//            turncommand.init();
        }
    }

    @Override
    public void loop() {
        Hardware.log("whacking jewel", "yes");
//        Hardware.log("flError", Hardware.instance.frontLeftDriveMotorWrapper.getError());
//        Hardware.log("frError", Hardware.instance.frontRightDriveMotorWrapper.getError());
//        drivecommand.loop();
//        turncommand.loop();
    }

    @Override
    public boolean shouldRemove() {
//        return drivecommand.shouldRemove();
//        return turncommand.shouldRemove();
        return timer.seconds() >= servoTravelTime;
    }

    @Override
    protected void end() {
//        drivecommand.interrupt();
//        turncommand.interrupt();
//        Subsystems.instance.jewelArm.moveWacker(JewelArm.JewelWackPosition.NEUTRAL);
    }
}
