package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveOffPad;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistance;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistanceCipherClose;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveForTime;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistanceCipherFar;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroCipherFar;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroPID;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnReturnClose;
import org.firstinspires.ftc.team7316.util.commands.flow.SequentialCommand;
import org.firstinspires.ftc.team7316.util.commands.flow.SimultaneousKeyCommand;
import org.firstinspires.ftc.team7316.util.commands.flow.Wait;
import org.firstinspires.ftc.team7316.util.commands.intake.IntakeForTime;
import org.firstinspires.ftc.team7316.util.commands.intake.MoveIntakeArm;
import org.firstinspires.ftc.team7316.util.commands.intake.RunIntake;
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
        DriveDistance backward = new DriveDistance(Constants.inchesToTicks(-Constants.FAR_CRYPTO_DISTANCE), 4);

        TurnGyroPID turnleft = new TurnGyroPID(90, 3);
        DriveDistanceCipherClose gotocrypto = new DriveDistanceCipherClose(Alliance.BLUE, DriveDistanceCipherClose.Position.FAR);
        TurnGyroPID turnleft2 = new TurnGyroPID(180, 3);

        DriveDistance approach = new DriveDistance(Constants.inchesToTicks(Constants.FAR_CRYPTO_APPROACH_BLUE), 2);
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
        DriveDistance forward = new DriveDistance(Constants.inchesToTicks(Constants.FAR_CRYPTO_DISTANCE), 4);

        TurnGyroPID turnleft = new TurnGyroPID(-90, 3);
        DriveDistanceCipherClose gotocrypto = new DriveDistanceCipherClose(Alliance.RED, DriveDistanceCipherClose.Position.FAR);
        TurnGyroPID turnright = new TurnGyroPID(0, 3);

        DriveDistance approach = new DriveDistance(Constants.inchesToTicks(Constants.FAR_CRYPTO_APPROACH_RED), 2);
        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        SequentialCommand bAndR = backUpAndRam();

        Command[] cmds = {clamp, wack, offPad, stop, align, stop2, forward, turnleft, gotocrypto, turnright, approach, outtake, bAndR};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand redFarMultiglyph() {
        MoveIntakeArm clamp = new MoveIntakeArm(Constants.INTAKE_CLAMP_GLYPH_POSITION);

        Command wack = wackJewelBasic(Alliance.RED);

        DriveOffPad offPad = new DriveOffPad(Alliance.RED);
        Wait stop = new Wait(0.1);
        DriveForTime align = new DriveForTime(Constants.OFF_PAD_POWER, Math.PI, 0.4);

        TurnGyroCipherFar turntocrypto = new TurnGyroCipherFar(Alliance.RED);

        DriveDistanceCipherFar gotocrypto = new DriveDistanceCipherFar(Alliance.RED);

        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);

        Command[] cmds = {clamp, wack, offPad, stop, align, turntocrypto, gotocrypto, outtake};
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
        DriveDistanceCipherClose gotocrypto = new DriveDistanceCipherClose(Alliance.BLUE, DriveDistanceCipherClose.Position.CLOSE);

        TurnGyroPID turn = new TurnGyroPID(90, 3);

        DriveDistance approach = new DriveDistance(Constants.inchesToTicks(Constants.CLOSE_CRYPTO_APPROACH_BLUE), 2);
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
        DriveDistanceCipherClose gotocrypto = new DriveDistanceCipherClose(Alliance.RED, DriveDistanceCipherClose.Position.CLOSE);

        TurnGyroPID turn = new TurnGyroPID(90, 3);

        DriveDistance approach = new DriveDistance(Constants.inchesToTicks(Constants.CLOSE_CRYPTO_APPROACH_RED), 2);
        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        SequentialCommand bAndR = backUpAndRam();

        Command[] cmds = {clamp, wack, offPad, stop, align, stop2, gotocrypto, turn, approach, outtake, bAndR};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand redCloseMutliglyph() {
//        return new SequentialCommand(redClose(), closeMultiglyph());
        MoveIntakeArm clamp = new MoveIntakeArm(Constants.INTAKE_CLAMP_GLYPH_POSITION);

        Command wack = wackJewelBasic(Alliance.RED);

        DriveOffPad offPad = new DriveOffPad(Alliance.RED);
        Wait stop = new Wait(0.1);
        DriveForTime align = new DriveForTime(Constants.OFF_PAD_POWER, Math.PI, 0.4);
        DriveDistanceCipherClose gotocrypto = new DriveDistanceCipherClose(Alliance.RED, DriveDistanceCipherClose.Position.CLOSE);

        TurnGyroPID turn = new TurnGyroPID(90, 2);

//        DriveDistance approach = new DriveDistance(Constants.CLOSE_CRYPTO_APPROACH_RED, 2);
        DriveForTime approach = new DriveForTime(0.6, 0, 0.4);
        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        SequentialCommand bAndR = releaseAndBackUp();

        Command[] cmds = {clamp, wack, offPad, stop, align, gotocrypto, turn, approach, outtake, bAndR, closeMultiglyph()};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand blueCloseMultiglyph() {
//        return new SequentialCommand(blueClose(), closeMultiglyph());
        MoveIntakeArm clamp = new MoveIntakeArm(Constants.INTAKE_CLAMP_GLYPH_POSITION);

        Command wack = wackJewelBasic(Alliance.BLUE);

        DriveOffPad offPad = new DriveOffPad(Alliance.BLUE);
        Wait stop = new Wait(0.1);
        DriveForTime align = new DriveForTime(Constants.OFF_PAD_POWER, 0, 0.4);
        DriveDistanceCipherClose gotocrypto = new DriveDistanceCipherClose(Alliance.BLUE, DriveDistanceCipherClose.Position.CLOSE);

        TurnGyroPID turn = new TurnGyroPID(90, 2);

//        DriveDistance approach = new DriveDistance(Constants.inchesToTicks(Constants.CLOSE_CRYPTO_APPROACH_BLUE), 2);
        DriveForTime approach = new DriveForTime(0.6, 0, 0.4);
        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        SequentialCommand bAndR = releaseAndBackUp();

        Command[] cmds = {clamp, wack, offPad, stop, align, gotocrypto, turn, approach, outtake, bAndR, closeMultiglyph()};
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

    public static SequentialCommand closeMultiglyph() {
//        StraightTurn moveToPit = new StraightTurn(-90, 0.7, 0.45);
        MoveIntakeArm openIntake = new MoveIntakeArm(0.8);
        TurnGyroPID turn180 = new TurnGyroPID(-90, 3, 120);
//        SimultaneousKeyCommand mowDownGlyphs = new SimultaneousKeyCommand(new DriveDistance(distancetopit), new RunIntake(-0.7));
//        DriveDistance driveToPit = new DriveDistance(Constants.inchesToTicks(distancetopit), 1800, 4);
//        DriveWhileIntake mowDownGlyphs = new DriveWhileIntake(-0.7, 0.4, 1.5);
        SimultaneousKeyCommand mowDownGlyphs = new SimultaneousKeyCommand(new DriveDistance(Constants.inchesToTicks(Constants.MULTIGLYPH_DIST_TO_PIT + Constants.MULTIGLYPH_DIST_TO_COLLECT), 1800, 5), new RunIntake(-0.7));
//        MoveIntakeArm closeIntake = new MoveIntakeArm(Constants.INTAKE_CLAMP_GLYPH_POSITION);
        SimultaneousKeyCommand closeIntake = new SimultaneousKeyCommand(new MoveIntakeArm(Constants.INTAKE_CLAMP_GLYPH_POSITION), new RunIntake(-0.7));

        double cryptoDist = Constants.MULTIGLYPH_DIST_TO_PIT + Constants.MULTIGLYPH_DIST_TO_COLLECT + Constants.MULTIGLYPH_BACKUP_DISTANCE;

        TurnReturnClose turn180_2 = new TurnReturnClose();
//        TurnGyroPID turn180_2 = new TurnGyroPID(90, 3, 120);

        DriveDistance backToCrypto = new DriveDistance(Constants.inchesToTicks(cryptoDist / Math.cos(Constants.MultiglyphRotate.LEFT.degrees)), 1800, 4);
        IntakeForTime outtake2 = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        SimultaneousKeyCommand bAndR2 = new SimultaneousKeyCommand(backUpAndRam(), new RunIntake(0.6));

        Command[] cmds = {openIntake, turn180, mowDownGlyphs, closeIntake, turn180_2, backToCrypto, outtake2, bAndR2};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand backUpAndRam() {
        Command[] cmds = {
                new MoveIntakeArm(0.8),
                new DriveDistance(Constants.inchesToTicks(-4), 1),
                new MoveIntakeArm(0),
                new DriveForTime(0.5, 0, 0.55),
                new DriveDistance(Constants.inchesToTicks(-6), 1)
        };
        return new SequentialCommand(cmds);
    }

    /**
     * Use only for multiglyph since it backs up many distance
     * @return
     */
    public static SequentialCommand releaseAndBackUp() {
        Command[] cmds = {
                new MoveIntakeArm(0.8),
                new DriveDistance(Constants.inchesToTicks(-Constants.MULTIGLYPH_BACKUP_DISTANCE), 1)
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
        DriveDistanceCipherClose gotocrypto = new DriveDistanceCipherClose(Alliance.RED, DriveDistanceCipherClose.Position.CLOSE);

        TurnGyroPID turn = new TurnGyroPID(90, 3);

        Command[] cmds = {gotocrypto, turn};
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
