package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveOffPad;
import org.firstinspires.ftc.team7316.util.commands.drive.StrafeCrypto;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistance;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistanceCipher;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveForTime;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroPID;
import org.firstinspires.ftc.team7316.util.commands.flow.SequentialCommand;
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
        Command wack = wackJewelBasic(Alliance.BLUE);

        DriveOffPad offPad = new DriveOffPad(Alliance.BLUE);
        Wait stop = new Wait(1);
        DriveDistance backward = new DriveDistance(-Constants.FAR_CRYPTO_DISTANCE, 4);

//        DriveForTime strafeleft = new DriveForTime(Constants.LEFT_POWER_TIME, -Math.PI/2, Constants.STRAFE_CRYPTO_CENTER_TIME);
        StrafeCrypto strafeleft = new StrafeCrypto(Alliance.BLUE);
        TurnGyroPID turn = new TurnGyroPID(180);

        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        DriveDistance ram = new DriveDistance(4, 4);
        DriveDistance backup = new DriveDistance(-4, 4);

        Command[] cmds = {wack, offPad, stop, backward, strafeleft, turn, outtake, ram, backup};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand farRedJewel() {
        Command wack = wackJewelBasic(Alliance.RED);

        DriveOffPad offPad = new DriveOffPad(Alliance.RED);
        Wait stop = new Wait(1);
        DriveDistance forward = new DriveDistance(Constants.FAR_CRYPTO_DISTANCE, 4);

//        DriveForTime strafeleft = new DriveForTime(Constants.LEFT_POWER_TIME, -Math.PI/2, Constants.STRAFE_CRYPTO_CENTER_TIME);
//        StrafeCrypto strafeleft = new StrafeCrypto(Alliance.RED);
        TurnGyroPID turnleft = new TurnGyroPID(-90, 3);
        DriveDistanceCipher gotocrypto = new DriveDistanceCipher(Alliance.RED, DriveDistanceCipher.Position.FAR);
        TurnGyroPID turnright = new TurnGyroPID(0, 3);

        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME); //add to constants later
        DriveDistance ram = new DriveDistance(4, 4);
        DriveDistance backup = new DriveDistance(-4, 4);

        Command[] cmds = {wack, offPad, stop, forward, turnleft, gotocrypto, turnright, outtake, ram, backup};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand closeBlueJewel() {
        Command wack = wackJewelBasic(Alliance.BLUE);

//        DriveForTime_PadModified offPad = new DriveForTime_PadModified(Constants.BLUE_OFF_PAD_TIME, Alliance.BLUE);
//        DriveForTime offPad = new DriveForTime(Constants.BACKWARD_POWER_FOR_TIME, Math.PI, Constants.BLUE_OFF_PAD_TIME);
        DriveOffPad offPad = new DriveOffPad(Alliance.BLUE);
        Wait stop = new Wait(1);
//        DriveDistance backward = new DriveDistance(-Constants.CLOSE_CRYPTO_DISTANCE, 10);
        DriveDistanceCipher gotocrypto = new DriveDistanceCipher(Alliance.BLUE, DriveDistanceCipher.Position.CLOSE);

        TurnGyroPID turn = new TurnGyroPID(90, 3);

        DriveDistance inchforward = new DriveDistance(Constants.CLOSE_CRYPTO_APPROACH_BLUE, 3);
        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        DriveDistance backup = new DriveDistance(-4, 3);

        Command[] cmds = {wack, offPad, stop, gotocrypto, turn, inchforward, outtake, backup};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand closeRedJewel() {
        Command wack = wackJewelBasic(Alliance.RED);

//        DriveForTime_PadModified offPad = new DriveForTime_PadModified(Constants.RED_OFF_PAD_TIME, Alliance.RED);
//        DriveForTime offPad = new DriveForTime(Constants.FORWARD_POWER_FOR_TIME, 0, Constants.RED_OFF_PAD_TIME);
        DriveOffPad offPad = new DriveOffPad(Alliance.RED);
        Wait stop = new Wait(1);
        //DriveDistance backward = new DriveDistance(Constants.CLOSE_CRYPTO_DISTANCE, 10);
        DriveDistanceCipher gotocrypto = new DriveDistanceCipher(Alliance.RED, DriveDistanceCipher.Position.CLOSE);

        TurnGyroPID turn = new TurnGyroPID(90, 3);

        DriveDistance inchforward = new DriveDistance(Constants.CLOSE_CRYPTO_APPROACH_RED, 3);
        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        DriveDistance backup = new DriveDistance(-4, 3);

        Command[] cmds = {wack, offPad, stop, gotocrypto, turn, inchforward, outtake, backup};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand wackJewelBasic(Alliance alliance) {
        MoveJewelArm movearmout = new MoveJewelArm(JewelArm.JewelArmPosition.OUT);
        PollColor pollColor = new PollColor();
        WackJewel wackjewel = new WackJewel(alliance);
        MoveJewelArm movearmin = new MoveJewelArm(JewelArm.JewelArmPosition.IN);

        Command[] cmds = {movearmout, pollColor, wackjewel, movearmin};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand driveTimeTesting() {
        DriveForTime d = new DriveForTime(0.7, 0, 1);
        Wait w = new Wait(551);
        Command[] cmds = {d, w};
        return new SequentialCommand(cmds);
    }
//
//    public static SequentialCommand driveStraight(double distance) {
//        DriveDistance drive = new DriveDistance(distance);
//        return new SequentialCommand(drive);
//    }

//    public static SequentialCommand driveStraightTurn(double distance, int angle) {
//        //DriveDistance drive = new DriveDistance(distance);
//        TurnGyroPID turn = new TurnGyroPID(angle);
//        Command[] cmds = {turn};
//        return new SequentialCommand(cmds);
//    }
}
