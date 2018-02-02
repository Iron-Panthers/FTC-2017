package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveOffPad;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistance;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistanceCipher;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveForTime;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroPID;
import org.firstinspires.ftc.team7316.util.commands.flow.SequentialCommand;
import org.firstinspires.ftc.team7316.util.commands.flow.Wait;
import org.firstinspires.ftc.team7316.util.commands.intake.DriveWhileIntake;
import org.firstinspires.ftc.team7316.util.commands.intake.IntakeForTime;
import org.firstinspires.ftc.team7316.util.commands.intake.MoveIntakeArm;
import org.firstinspires.ftc.team7316.util.commands.jewelarm.MoveJewelArm;
import org.firstinspires.ftc.team7316.util.commands.jewelarm.WackJewel;
import org.firstinspires.ftc.team7316.util.commands.sensors.PollColor;
import org.firstinspires.ftc.team7316.util.subsystems.JewelArm;

/**
 * Created by andrew on 11/2/16.
 * All the sequential commands to run
 */
public class AutoCodes {

    /*
    public static SimultaneousCommands robotDriveDistanceAccurate(double distance, double power) {
        DriveDistanceAccurate leftMotor = new DriveDistanceAccurate(Constants.inchesToTicks(distance), power, Hardware.instance.leftDriveMotor);
        DriveDistanceAccurate rightMotor = new DriveDistanceAccurate(Constants.inchesToTicks(distance), power, Hardware.instance.rightDriveMotor);
        Command[] both = {leftMotor, rightMotor};

        SimultaneousCommands bothDrive = new SimultaneousCommands(both);
        return bothDrive;
    }
    */

    public static SequentialCommand blueFar() {
        MoveIntakeArm clamp = new MoveIntakeArm(Constants.INTAKE_CLAMP_GLYPH_POSITION);

        Command wack = wackJewelBasic(Alliance.BLUE);

        DriveOffPad offPad = new DriveOffPad(Alliance.BLUE);
        Wait stop = new Wait(0.5);
        DriveForTime align = new DriveForTime(Constants.OFF_PAD_POWER, 0, 1);
        Wait stop2 = new Wait(0.5);
//        TurnUntilKey detectkey = new TurnUntilKey(-1, -90);
        DriveDistance backward = new DriveDistance(-Constants.FAR_CRYPTO_DISTANCE, 4);

        TurnGyroPID turnleft = new TurnGyroPID(90, 3);
        DriveDistanceCipher gotocrypto = new DriveDistanceCipher(Alliance.BLUE, DriveDistanceCipher.Position.FAR);
        TurnGyroPID turnleft2 = new TurnGyroPID(180, 3);

        DriveDistance approach = new DriveDistance(Constants.FAR_CRYPTO_APPROACH_BLUE, 2);
        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        SequentialCommand bAndR = backUpAndRam();

        Command[] cmds = {clamp, wack, offPad, stop, align, stop2, backward, turnleft, gotocrypto, turnleft2, approach, outtake, bAndR};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand redFar() {
        MoveIntakeArm clamp = new MoveIntakeArm(Constants.INTAKE_CLAMP_GLYPH_POSITION);

        Command wack = wackJewelBasic(Alliance.RED);

        DriveOffPad offPad = new DriveOffPad(Alliance.RED);
        Wait stop = new Wait(0.5);
        DriveForTime align = new DriveForTime(Constants.OFF_PAD_POWER, Math.PI, 1);
        Wait stop2 = new Wait(0.5);
//        TurnUntilKey detectkey = new TurnUntilKey(1, 90);
        DriveDistance forward = new DriveDistance(Constants.FAR_CRYPTO_DISTANCE, 4);

        TurnGyroPID turnleft = new TurnGyroPID(-90, 3);
        DriveDistanceCipher gotocrypto = new DriveDistanceCipher(Alliance.RED, DriveDistanceCipher.Position.FAR);
        TurnGyroPID turnright = new TurnGyroPID(0, 3);

        DriveDistance approach = new DriveDistance(Constants.FAR_CRYPTO_APPROACH_RED, 2);
        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        SequentialCommand bAndR = backUpAndRam();

        Command[] cmds = {clamp, wack, offPad, stop, align, stop2, forward, turnleft, gotocrypto, turnright, approach, outtake, bAndR};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand blueClose() {
        MoveIntakeArm clamp = new MoveIntakeArm(Constants.INTAKE_CLAMP_GLYPH_POSITION);

        Command wack = wackJewelBasic(Alliance.BLUE);

        DriveOffPad offPad = new DriveOffPad(Alliance.BLUE);
        Wait stop = new Wait(0.5);
        DriveForTime align = new DriveForTime(Constants.OFF_PAD_POWER, 0, 1);
        Wait stop2 = new Wait(0.5);
//        TurnUntilKey deteckkey = new TurnUntilKey(-1, -90);
        DriveDistanceCipher gotocrypto = new DriveDistanceCipher(Alliance.BLUE, DriveDistanceCipher.Position.CLOSE);

        TurnGyroPID turn = new TurnGyroPID(90, 3);

        DriveDistance approach = new DriveDistance(Constants.CLOSE_CRYPTO_APPROACH_BLUE, 2);
        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        SequentialCommand bAndR = backUpAndRam();

        Command[] cmds = {clamp, wack, offPad, stop, align, stop2, gotocrypto, turn, approach, outtake, bAndR};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand redClose() {
        MoveIntakeArm clamp = new MoveIntakeArm(Constants.INTAKE_CLAMP_GLYPH_POSITION);

        Command wack = wackJewelBasic(Alliance.RED);

        DriveOffPad offPad = new DriveOffPad(Alliance.RED);
        Wait stop = new Wait(0.5);
        DriveForTime align = new DriveForTime(Constants.OFF_PAD_POWER, Math.PI, 1);
        Wait stop2 = new Wait(0.5);
//        TurnUntilKey detectkey = new TurnUntilKey(1, 90);
        DriveDistanceCipher gotocrypto = new DriveDistanceCipher(Alliance.RED, DriveDistanceCipher.Position.CLOSE);

        TurnGyroPID turn = new TurnGyroPID(90, 3);

        DriveDistance approach = new DriveDistance(Constants.CLOSE_CRYPTO_APPROACH_RED, 2);
        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        SequentialCommand bAndR = backUpAndRam();

        Command[] cmds = {clamp, wack, offPad, stop, align, stop2, gotocrypto, turn, approach, outtake, bAndR};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand redCloseMutliglyph() {
//        return new SequentialCommand(redClose(), closeMultiglyph());
        return new SequentialCommand(redClose());
    }

    public static SequentialCommand blueCloseMultiglyph() {
        return new SequentialCommand(blueClose(), closeMultiglyph());
    }

    public static SequentialCommand wackJewelBasic(Alliance alliance) {
        MoveJewelArm movearmout = new MoveJewelArm(JewelArm.JewelArmPosition.OUT);
        PollColor pollColor = new PollColor();
        WackJewel wackjewel = new WackJewel(alliance);
        MoveJewelArm movearmin = new MoveJewelArm(JewelArm.JewelArmPosition.IN);

        Command[] cmds = {movearmout, pollColor, wackjewel, movearmin};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand closeMultiglyph() {
//        StraightTurn moveToPit = new StraightTurn(-90, 0.7, 0.45);
        TurnGyroPID turn180 = new TurnGyroPID(-90, 4, 100);
        DriveDistance driveToPit = new DriveDistance(25);
        DriveWhileIntake collectGlyphs = new DriveWhileIntake(-0.7, 0.4, 5);
        TurnGyroPID turn180_2 = new TurnGyroPID(90, 4, 100);

        DriveDistance backToCrypto = new DriveDistance(35);
        IntakeForTime outtake2 = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        SequentialCommand bAndR2 = backUpAndRam();

        Command[] cmds = {turn180, driveToPit, collectGlyphs, turn180_2, backToCrypto, outtake2, bAndR2};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand backUpAndRam() {
        Command[] cmds = {
                new MoveIntakeArm(0.8),
                new DriveDistance(-4, 1.5),
                new MoveIntakeArm(0),
                new DriveForTime(0.5, 0, 0.55),
                new DriveDistance(-Constants.FAR_CRYPTO_APPROACH_RED - 14, 2)
        };
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand driveTimeTesting() {
        DriveForTime d = new DriveForTime(0.7, 0, 1);
        Wait w = new Wait(551);
        Command[] cmds = {d, w};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand phase2Test() {
        DriveDistanceCipher gotocrypto = new DriveDistanceCipher(Alliance.BLUE, DriveDistanceCipher.Position.CLOSE);

        TurnGyroPID turn = new TurnGyroPID(90, 3);

        DriveDistance inchforward = new DriveDistance(Constants.CLOSE_CRYPTO_APPROACH_BLUE, 3);
        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        DriveDistance backup = new DriveDistance(-4, 3);

        Command[] cmds = {gotocrypto, turn, inchforward, outtake, backup};
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
