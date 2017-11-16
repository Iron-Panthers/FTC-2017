package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveDistance;
import org.firstinspires.ftc.team7316.util.commands.flow.SequentialCommand;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroPID;
import org.firstinspires.ftc.team7316.util.commands.sensors.PollColor;
import org.firstinspires.ftc.team7316.util.subsystems.JewelArm;

/**
 * Created by andrew on 11/2/16.
 * All the sequential commands to run
 */
public class AutoCodes {

    /*public static SimultaneousCommands robotDriveDistanceAccurate(double distance, double power) {
        DriveDistanceAccurate leftMotor = new DriveDistanceAccurate(Constants.distanceToTicks(distance), power, Hardware.instance.leftDriveMotor);
        DriveDistanceAccurate rightMotor = new DriveDistanceAccurate(Constants.distanceToTicks(distance), power, Hardware.instance.rightDriveMotor);
        Command[] both = {leftMotor, rightMotor};

        SimultaneousCommands bothDrive = new SimultaneousCommands(both);
        return bothDrive;
    }*/

    public static SequentialCommand jewelWack(Alliance alliance) {
        MoveJewelArm movearmout = new MoveJewelArm(JewelArm.JewelArmPosition.OUT);
        PollColor pollColor = new PollColor();
        WackJewel wackjewel = new WackJewel(alliance);
        MoveJewelArm movearmin = new MoveJewelArm(JewelArm.JewelArmPosition.IN);
        Command[] cmds = {movearmout, pollColor, wackjewel, movearmin};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand driveStraight(double distance, double power) {
        DriveDistance drive = new DriveDistance(distance);
        return new SequentialCommand(drive);
    }

    public static SequentialCommand driveStraightTurn(double distance, int angle, double power) {
        //DriveDistance drive = new DriveDistance(distance, power);
        TurnGyroPID turn = new TurnGyroPID(angle);
        Command[] cmds = {turn};
        return new SequentialCommand(cmds);
    }
}
