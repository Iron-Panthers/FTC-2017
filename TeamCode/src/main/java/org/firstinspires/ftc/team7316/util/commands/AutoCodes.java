package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.CryptoLocations;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.IntWrapper;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveOffPad;
import org.firstinspires.ftc.team7316.util.commands.drive.GetGlyphAndReturn;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistance;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistanceCipherClose;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveForTime;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistanceCipherFar;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistanceCryptoVP;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroCipherFar;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroCryptoVP;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroPID;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnReturnClose;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnUntilKey;
import org.firstinspires.ftc.team7316.util.commands.flow.SequentialCommand;
import org.firstinspires.ftc.team7316.util.commands.flow.SimultaneousKeyCommand;
import org.firstinspires.ftc.team7316.util.commands.flow.Wait;
import org.firstinspires.ftc.team7316.util.commands.flow.WaitAndLook;
import org.firstinspires.ftc.team7316.util.commands.intake.IntakeForTime;
import org.firstinspires.ftc.team7316.util.commands.intake.MoveIntakeArm;
import org.firstinspires.ftc.team7316.util.commands.intake.RunIntake;
import org.firstinspires.ftc.team7316.util.commands.jewelarm.MoveJewelArm;
import org.firstinspires.ftc.team7316.util.commands.jewelarm.WackJewel;
import org.firstinspires.ftc.team7316.util.commands.sensors.PollColor;
import org.firstinspires.ftc.team7316.util.commands.sensors.SetConfigVuforia;
import org.firstinspires.ftc.team7316.util.commands.sensors.UpdateVuforia;
import org.firstinspires.ftc.team7316.util.subsystems.JewelArm;

/**
 * Created by andrew on 11/2/16.
 * All the sequential commands to run
 */
public class AutoCodes {

    /**
     * Automatically selects a legacy auto sequence as a failsafe for the VP autos.
     * @param code the ID of the current auto sequence.
     */
    public static SequentialCommand oldAutoForCode(int code) {
        if (code == CryptoLocations.CLOSE_RED_AUTO) {
            return redCloseMutliglyph();
        }
        else if (code == CryptoLocations.FAR_RED_AUTO) {
            return redFar();
        }
        else if (code == CryptoLocations.CLOSE_BLUE_AUTO) {
            return blueCloseMultiglyph();
        }
        else if (code == CryptoLocations.FAR_BLUE_AUTO) {
            return blueFar();
        }
        return null;
    }

    //--------------------------------------------------------------//
    //-------------------------------RED FAR AUTOS-------------------------------//
    //--------------------------------------------------------------//

    /**
     * Used as a failsafe for {@link #redFarVP()}
     */
    public static SequentialCommand redFar() {
        TurnGyroPID reset = new TurnGyroPID(0, 2);

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

        Command[] cmds = {reset, align, stop2, forward, turnleft, gotocrypto, turnright, approach, outtake, bAndR};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand redFarVP() {
        MoveIntakeArm clamp = new MoveIntakeArm(Constants.INTAKE_CLAMP_GLYPH_POSITION);

        Command wack = wackJewelBasic(Alliance.RED);

        DriveForTime offPad = new DriveForTime(0.3, 0, 1.25);
        Wait stop = new Wait(0.1);

        Command[] cmds = {clamp, wack, offPad, stop, putGlyph(true, CryptoLocations.FAR_RED_AUTO, false)};//, closeMultiglyphVP(-60, CryptoLocations.CLOSE_RED_AUTO)};
        return new SequentialCommand(cmds);
    }

    /**
     * To be implemented
     */
    public static SequentialCommand redFarMultiglyph() {
        Command[] cmds = {};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand redFarLegacy() {
        MoveIntakeArm clamp = new MoveIntakeArm(Constants.INTAKE_CLAMP_GLYPH_POSITION);

        Command wack = wackJewelBasic(Alliance.RED);

        DriveOffPad offPad = new DriveOffPad(Alliance.RED);

        DriveForTime align = new DriveForTime(Constants.OFF_PAD_POWER, Math.PI, 1);
        Wait stop2 = new Wait(0.2);
        DriveDistance forward = new DriveDistance(Constants.inchesToTicks(Constants.FAR_CRYPTO_DISTANCE), 4);

        TurnGyroPID turnleft = new TurnGyroPID(-90, 3);
        DriveDistanceCipherClose gotocrypto = new DriveDistanceCipherClose(Alliance.RED, DriveDistanceCipherClose.Position.FAR);
        TurnGyroPID turnright = new TurnGyroPID(0, 3);

        DriveDistance approach = new DriveDistance(Constants.inchesToTicks(Constants.FAR_CRYPTO_APPROACH_RED), 2);
        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        SequentialCommand bAndR = backUpAndRam();

        Command[] cmds = {clamp, wack, offPad, align, stop2, forward, turnleft, gotocrypto, turnright, approach, outtake, bAndR};
        return new SequentialCommand(cmds);
    }

    //--------------------------------------------------------------//
    //-------------------------------BLUE FAR AUTOS-------------------------------//
    //--------------------------------------------------------------//

    /**
     * Used as a failsafe for {@link #blueFarVP()}
     */
    public static SequentialCommand blueFar() {
        TurnGyroPID reset = new TurnGyroPID(0, 2);

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

        Command[] cmds = {reset, align, stop2, backward, turnleft, gotocrypto, turnleft2, approach, outtake, bAndR};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand blueFarVP() {
        MoveIntakeArm clamp = new MoveIntakeArm(Constants.INTAKE_CLAMP_GLYPH_POSITION);

        Command wack = wackJewelBasic(Alliance.BLUE);

        DriveForTime offPad = new DriveForTime(0.3, Math.PI, 1.25);
        Wait stop = new Wait(0.1);

        Command[] cmds = {clamp, wack, offPad, stop, putGlyph(true, CryptoLocations.FAR_BLUE_AUTO, false)};//, closeMultiglyphVP(-60, CryptoLocations.CLOSE_RED_AUTO)};
        return new SequentialCommand(cmds);
    }

    /**
     * To be implemented
     */
    public static SequentialCommand blueFarMultiglyph() {
        Command[] cmds = {};
        return new SequentialCommand(cmds);
    }

    /**
     * Old blue far auto in case the VP auto becomes unreliable
     */
    public static SequentialCommand blueFarLegacy() {
        MoveIntakeArm clamp = new MoveIntakeArm(Constants.INTAKE_CLAMP_GLYPH_POSITION);

        Command wack = wackJewelBasic(Alliance.BLUE);

        DriveOffPad offPad = new DriveOffPad(Alliance.BLUE);
        DriveForTime align = new DriveForTime(Constants.OFF_PAD_POWER, 0, 1);
        Wait stop2 = new Wait(0.2);
        DriveDistance backward = new DriveDistance(Constants.inchesToTicks(-Constants.FAR_CRYPTO_DISTANCE), 4);

        TurnGyroPID turnleft = new TurnGyroPID(90, 3);
        DriveDistanceCipherClose gotocrypto = new DriveDistanceCipherClose(Alliance.BLUE, DriveDistanceCipherClose.Position.FAR);
        TurnGyroPID turnleft2 = new TurnGyroPID(180, 3);

        DriveDistance approach = new DriveDistance(Constants.inchesToTicks(Constants.FAR_CRYPTO_APPROACH_BLUE), 2);
        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        SequentialCommand bAndR = backUpAndRam();

        Command[] cmds = {clamp, wack, offPad, align, stop2, backward, turnleft, gotocrypto, turnleft2, approach, outtake, bAndR};
        return new SequentialCommand(cmds);
    }

    //--------------------------------------------------------------//
    //-------------------------------RED CLOSE AUTOS-------------------------------//
    //--------------------------------------------------------------//

    /**
     * Very old red close auto. Use only for reference.
     */
    @Deprecated
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

    /**
     * Used as a failsafe for {@link #redCloseMultiglyphVP()}
     */
    public static SequentialCommand redCloseMutliglyph() {
        TurnGyroPID reset = new TurnGyroPID(0, 2);

        DriveForTime align = new DriveForTime(Constants.OFF_PAD_POWER, Math.PI, 0.4);
        DriveDistanceCipherClose gotocrypto = new DriveDistanceCipherClose(Alliance.RED, DriveDistanceCipherClose.Position.CLOSE);

        TurnGyroPID turn = new TurnGyroPID(90, 2);

//        DriveDistance approach = new DriveDistance(Constants.CLOSE_CRYPTO_APPROACH_RED, 2);
        DriveForTime approach = new DriveForTime(0.6, 0, 0.4);
        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        SequentialCommand bAndR = releaseAndBackUp();

        Command[] cmds = {reset, align, gotocrypto, turn, approach, outtake, bAndR, closeMultiglyph()};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand redCloseMultiglyphVP() {
        MoveIntakeArm clamp = new MoveIntakeArm(Constants.INTAKE_CLAMP_GLYPH_POSITION);

        Command wack = wackJewelBasic(Alliance.RED);

        DriveForTime offPad = new DriveForTime(0.3, 0, 1.25);
        Wait stop = new Wait(0.1);

        Command[] cmds = {clamp, wack, offPad, stop, putGlyph(true, CryptoLocations.CLOSE_RED_AUTO, true), closeMultiglyphVP(-75, CryptoLocations.CLOSE_RED_AUTO, false)};//, closeMultiglyphVP(-60, CryptoLocations.CLOSE_RED_AUTO)};
        return new SequentialCommand(cmds);
    }

    //--------------------------------------------------------------//
    //-------------------------------BLUE CLOSE AUTOS-------------------------------//
    //--------------------------------------------------------------//

    /**
     * Very old blue close auto. Use only for reference.
     */
    @Deprecated
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

    /**
     * Used as a failsafe for {@link #blueCloseMultiglyphVP()}
     * With current auto design for {@link #blueCloseMultiglyphVP()}, this failsafe will not work right now.
     */
    public static SequentialCommand blueCloseMultiglyph() {
        TurnGyroPID reset = new TurnGyroPID(0, 2);

        DriveForTime align = new DriveForTime(Constants.OFF_PAD_POWER, 0, 0.4);
        DriveDistanceCipherClose gotocrypto = new DriveDistanceCipherClose(Alliance.BLUE, DriveDistanceCipherClose.Position.CLOSE);

        TurnGyroPID turn = new TurnGyroPID(90, 2);

//        DriveDistance approach = new DriveDistance(Constants.inchesToTicks(Constants.CLOSE_CRYPTO_APPROACH_BLUE), 2);
        DriveForTime approach = new DriveForTime(0.6, 0, 0.4);
        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        SequentialCommand bAndR = releaseAndBackUp();

        Command[] cmds = {reset, align, gotocrypto, turn, approach, outtake, bAndR, closeMultiglyph()};
        return new SequentialCommand(cmds);
    }

    /**
     * Utilizes the blue far pictograph instead for more consistence.
     */
    public static SequentialCommand blueCloseMultiglyphVP() {
        MoveIntakeArm clamp = new MoveIntakeArm(Constants.INTAKE_CLAMP_GLYPH_POSITION);

        Command wack = wackJewelBasic(Alliance.BLUE);

        DriveOffPad offPad = new DriveOffPad(Alliance.BLUE);
        DriveDistance driveMore = new DriveDistance(-Constants.inchesToTicks(20), 3.5);
        Wait stop = new Wait(0.1);

        Command[] cmds = {clamp, wack, offPad, driveMore, stop, putGlyph(true, CryptoLocations.CLOSE_RED_AUTO, true), closeMultiglyphVP(-75, CryptoLocations.CLOSE_BLUE_AUTO, false)};//, closeMultiglyphVP(-45, CryptoLocations.CLOSE_BLUE_AUTO)};
        return new SequentialCommand(cmds);
    }

    //--------------------------------------------------------------//
    //-------------------------------MISC FUNCTIONS-------------------------------//
    //--------------------------------------------------------------//

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

        double cryptoDist = Constants.MULTIGLYPH_DIST_TO_PIT + Constants.MULTIGLYPH_DIST_TO_COLLECT;

        TurnReturnClose turn180_2 = new TurnReturnClose();
//        TurnGyroPID turn180_2 = new TurnGyroPID(90, 3, 120);

        DriveDistance backToCrypto = new DriveDistance(Constants.inchesToTicks(cryptoDist), 1800, 4);
        IntakeForTime outtake2 = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        SimultaneousKeyCommand bAndR2 = new SimultaneousKeyCommand(backUpAndRam(), new RunIntake(0.6));

        Command[] cmds = {openIntake, turn180, mowDownGlyphs, closeIntake, turn180_2, backToCrypto, outtake2, bAndR2};
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand putGlyph(boolean useKeyMark, int autoLocation, boolean continueMulti, double angle) {
        TurnUntilKey facePicto = new TurnUntilKey(angle, autoLocation);

        SetConfigVuforia config = new SetConfigVuforia(useKeyMark, autoLocation);

        TurnGyroCryptoVP turnToCrypto = new TurnGyroCryptoVP();

        IntWrapper DISTANCE_TRAVELLED = new IntWrapper(0);
        DriveDistanceCryptoVP driveToCrypto = new DriveDistanceCryptoVP(DISTANCE_TRAVELLED);

        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);

        DriveDistance backup = new DriveDistance(DISTANCE_TRAVELLED, 3);

        Command[] cmds;
        if(continueMulti) {
            cmds = new Command[]{facePicto, new WaitAndLook(0.3), config, turnToCrypto, driveToCrypto, outtake, backup};
        }
        else {
            cmds = new Command[]{facePicto, new WaitAndLook(0.3), config, turnToCrypto, driveToCrypto, outtake};
        }
        return new SequentialCommand(cmds);
    }

    public static SequentialCommand putGlyph(boolean useKeyMark, int autoLocation, boolean continueMulti) {
        if (autoLocation == CryptoLocations.CLOSE_RED_AUTO || autoLocation == CryptoLocations.FAR_RED_AUTO) {
            return putGlyph(useKeyMark, autoLocation, continueMulti, CryptoLocations.RED_TURN_TO_PICTO);
        } else {
            return putGlyph(useKeyMark, autoLocation, continueMulti, CryptoLocations.BLUE_TURN_TO_PICTO);
        }
    }

    public static SequentialCommand closeMultiglyphVP(double driveAngle, int automode, boolean continueMulti) {
        GetGlyphAndReturn get = new GetGlyphAndReturn(driveAngle);
        SequentialCommand put = putGlyph(false, automode, continueMulti, 48);

        Command[] cmds = {get, put};
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

}
