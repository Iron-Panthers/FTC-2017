package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveDistance;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveForTime;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveForTime_PadModified;
import org.firstinspires.ftc.team7316.util.commands.drive.Strafe;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveDistance_PadModified;
import org.firstinspires.ftc.team7316.util.commands.flow.SequentialCommand;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroPID;
import org.firstinspires.ftc.team7316.util.commands.flow.Wait;
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

    public static SequentialCommand farBlueJewel() {
        MoveJewelArm movearmout = new MoveJewelArm(JewelArm.JewelArmPosition.OUT);
        PollColor pollColor = new PollColor();
        WackJewel wackjewel = new WackJewel(Alliance.BLUE);
        MoveJewelArm movearmin = new MoveJewelArm(JewelArm.JewelArmPosition.IN);
        DriveDistance backward = new DriveDistance(-Constants.PARKING_FOWARD_DISTANCE);
        Strafe strafeleft = new Strafe(-Constants.CRYPTOBOX_STRAFE_DISTANCE);

        Command[] cmds = {movearmout, pollColor, wackjewel, movearmin};
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
        Wait stop = new Wait(1);
        DriveDistance forward = new DriveDistance(Constants.PARKING_FOWARD_DISTANCE);
        DriveForTime strafe = new DriveForTime(Constants.LEFT_POWER_TIME, -Math.PI/2, Constants.STRAFE_LEFT_TIME);

        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME); //add to constants later

        DriveDistance backup = new DriveDistance(-2);

        Command[] cmds = {movearmout, pollColor, wackjewel, movearmin, offPad, stop, forward, strafe, outtake, backup};
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
