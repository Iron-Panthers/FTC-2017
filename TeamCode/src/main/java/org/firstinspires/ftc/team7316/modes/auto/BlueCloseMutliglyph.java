package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.CryptoLocations;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.commands.sensors.UpdateVuforia;

/**
 * Created by jerry on 1/30/18.
 */

@Autonomous(name = "blue close multiglyph legacy", group = "game autos")
public class BlueCloseMutliglyph extends AutoBaseOpMode {

    @Override
    public void onInit() {
        Hardware.instance.vuforiaCameraWrapper.startTracking();
        Scheduler.instance.add(AutoCodes.blueCloseMultiglyphLegacy());
        Scheduler.instance.add(new UpdateVuforia());
    }

    @Override
    public void onLoop() {

    }

//    @Override
//    public Command[] createCommands() {
//        MoveIntakeArm clamp = new MoveIntakeArm(Constants.INTAKE_CLAMP_GLYPH_POSITION);
//
//        MoveJewelArm movearmout = new MoveJewelArm(JewelArm.JewelArmPosition.OUT);
//        PollColor pollColor = new PollColor();
//        WackJewel wackjewel = new WackJewel(Alliance.BLUE);
//        MoveJewelArm movearmin = new MoveJewelArm(JewelArm.JewelArmPosition.IN);
//
//        DriveOffPad offPad = new DriveOffPad(Alliance.BLUE);
//        Wait stop = new Wait(0.5);
//        DriveForTime align = new DriveForTime(Constants.OFF_PAD_POWER, 0, 1);
//        Wait stop2 = new Wait(0.5);
////        TurnUntilKey deteckkey = new TurnUntilKey(-1, -90);
//        DriveDistanceCipherClose gotocrypto = new DriveDistanceCipherClose(Alliance.BLUE, DriveDistanceCipherClose.Position.CLOSE);
//
//        TurnGyroPID turn = new TurnGyroPID(90, 3);
//
//        DriveDistance approach = new DriveDistance(Constants.CLOSE_CRYPTO_APPROACH_BLUE, 2);
//        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
//        BackupAndRam bAndR = new BackupAndRam();
//
//        StraightTurn moveToPit = new StraightTurn(-90, 0.7, 0.45);
//        DriveWhileIntake collectGlyphs = new DriveWhileIntake(0.7, 0.35, 3);
//        TurnGyroPID turn180 = new TurnGyroPID(90, 4, 100);
//
//        DriveDistance backToCrypto = new DriveDistance(60);
//        IntakeForTime outtake2 = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
//        BackupAndRam bAndR2 = new BackupAndRam();
//
//        Command[] cmds = {clamp, movearmout, pollColor, wackjewel, movearmin, offPad, stop, align, stop2, gotocrypto, turn, approach, outtake, bAndR,
//                moveToPit, collectGlyphs, turn180, backToCrypto, outtake2, bAndR2};
//        return cmds;
//    }
}
