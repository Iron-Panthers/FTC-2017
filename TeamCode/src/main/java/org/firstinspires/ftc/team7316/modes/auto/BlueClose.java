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
 * Created by jerry on 11/19/17.
 */

@Autonomous(name = "blue close", group = "game autos")
@Disabled
public class BlueClose extends AutoBaseOpMode {

    @Override
    public void onInit() {
        Hardware.instance.vuforiaCameraWrapper.startTracking();
        Scheduler.instance.add(AutoCodes.blueClose());
        Scheduler.instance.add(new UpdateVuforia());
    }

    @Override
    public void onLoop() {

    }
//
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
//        Command[] cmds = {clamp, movearmout, pollColor, wackjewel, movearmin, offPad, stop, align, stop2, gotocrypto, turn, approach, outtake, bAndR};
//        return cmds;
//    }
}
