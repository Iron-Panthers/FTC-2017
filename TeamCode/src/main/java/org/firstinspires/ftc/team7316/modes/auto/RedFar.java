package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.commands.sensors.UpdateVuforia;

/**
 * Created by jerry on 11/18/17.
 */

@Autonomous(name = "red far", group = "game autos")
public class RedFar extends AutoBaseOpMode {

    @Override
    public void onInit() {
        Hardware.instance.vuforiaCameraWrapper.startTracking();
        Scheduler.instance.add(AutoCodes.redFar());
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
//        WackJewel wackjewel = new WackJewel(Alliance.RED);
//        MoveJewelArm movearmin = new MoveJewelArm(JewelArm.JewelArmPosition.IN);
//
//        DriveOffPad offPad = new DriveOffPad(Alliance.RED);
//        Wait stop = new Wait(0.5);
//        DriveForTime align = new DriveForTime(Constants.OFF_PAD_POWER, Math.PI, 1);
//        Wait stop2 = new Wait(0.5);
////        TurnUntilKey detectkey = new TurnUntilKey(1, 90);
//        DriveDistance forward = new DriveDistance(Constants.FAR_CRYPTO_DISTANCE, 4);
//
//        TurnGyroPID turnleft = new TurnGyroPID(-90, 3);
//        DriveDistanceCipherClose gotocrypto = new DriveDistanceCipherClose(Alliance.RED, DriveDistanceCipherClose.Position.FAR);
//        TurnGyroPID turnright = new TurnGyroPID(0, 3);
//
//        DriveDistance approach = new DriveDistance(Constants.FAR_CRYPTO_APPROACH_RED, 2);
//        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
//        BackupAndRam bAndR = new BackupAndRam();
//
//        Command[] cmds = {clamp, movearmout, pollColor, wackjewel, movearmin, offPad, stop, align, stop2, forward, turnleft, gotocrypto, turnright, approach, outtake, bAndR};
//        return cmds;
//    }
}
