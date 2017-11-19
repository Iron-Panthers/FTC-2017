package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveDistance;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveForTime;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveForTime_PadModified;
import org.firstinspires.ftc.team7316.util.commands.drive.Strafe;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.DriveDistance_PadModified;
import org.firstinspires.ftc.team7316.util.commands.flow.SequentialCommand;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroPID;
import org.firstinspires.ftc.team7316.util.commands.intake.IntakeForTime;
import org.firstinspires.ftc.team7316.util.commands.sensors.PollColor;
import org.firstinspires.ftc.team7316.util.subsystems.JewelArm;

/**
 * Created by andrew on 11/2/16.
 * All the sequential commands to run
 */
public class AutoCodes {

    /*public static SimultaneousCommands robotDriveDistanceAccurate(double distance, double power) {
        DriveDistanceAccurate leftMotor = new DriveDistanceAccurate(Constants.inchesToTicks(distance), power, Hardware.instance.leftDriveMotor);
        DriveDistanceAccurate rightMotor = new DriveDistanceAccurate(Constants.inchesToTicks(distance), power, Hardware.instance.rightDriveMotor);
        Command[] both = {leftMotor, rightMotor};

        SimultaneousCommands bothDrive = new SimultaneousCommands(both);
        return bothDrive;
    }*/

    public static SequentialCommand closeBlueJewel() {
        MoveJewelArm movearmout = new MoveJewelArm(JewelArm.JewelArmPosition.OUT);
        PollColor pollColor = new PollColor();
        WackJewel wackjewel = new WackJewel(Alliance.BLUE);
        MoveJewelArm movearmin = new MoveJewelArm(JewelArm.JewelArmPosition.IN);
        DriveDistance foward = new DriveDistance(Constants.PARKING_FOWARD_DISTANCE);
//        DriveForTime foward = new DriveForTime(0.3, 0, 2);
//        DriveForTime strafe = new DriveForTime(0.8, -Math.PI/2, 2.5);
        Strafe strafeleft = new Strafe(-Constants.CRYPTOBOX_STRAFE_DISTANCE);

        Command[] cmds = {movearmout, pollColor, wackjewel, movearmin, foward, strafeleft};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand farRedJewel() {
        MoveJewelArm movearmout = new MoveJewelArm(JewelArm.JewelArmPosition.OUT);
        PollColor pollColor = new PollColor();
        WackJewel wackjewel = new WackJewel(Alliance.RED);
        MoveJewelArm movearmin = new MoveJewelArm(JewelArm.JewelArmPosition.IN);

        //DriveDistance foward = new DriveDistance(Constants.PARKING_FOWARD_DISTANCE + Hardware.instance.colorWrapper.distanceincrease);
        //Strafe strafeleft = new Strafe(-Constants.CRYPTOBOX_STRAFE_DISTANCE);

        DriveForTime_PadModified offPad = new DriveForTime_PadModified(Constants.FORWARD_POWER_TIME, 0, Constants.OFF_PAD_TIME);
        DriveDistance_PadModified forward = new DriveDistance_PadModified(Constants.PARKING_FOWARD_DISTANCE);
        DriveForTime strafe = new DriveForTime(Constants.LEFT_POWER_TIME, -Math.PI/2, Constants.STRAFE_LEFT_TIME);

        IntakeForTime outtake = new IntakeForTime(-0.7, 1); //add to constants later

        Command[] cmds = {movearmout, pollColor, wackjewel, movearmin, offPad, forward, strafe, outtake};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand driveStraight(double distance) {
        DriveDistance drive = new DriveDistance(distance);
        return new SequentialCommand(drive);
    }

    public static SequentialCommand driveStraightTurn(double distance, int angle) {
        //DriveDistance drive = new DriveDistance(distance);
        TurnGyroPID turn = new TurnGyroPID(angle);
        Command[] cmds = {turn};
        return new SequentialCommand(cmds);
    }
}
