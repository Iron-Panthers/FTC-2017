package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveForTime;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveOffPad;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistance;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistanceCipher;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroPID;
import org.firstinspires.ftc.team7316.util.commands.flow.Wait;
import org.firstinspires.ftc.team7316.util.commands.intake.IntakeForTime;
import org.firstinspires.ftc.team7316.util.commands.jewelarm.MoveJewelArm;
import org.firstinspires.ftc.team7316.util.commands.jewelarm.WackJewel;
import org.firstinspires.ftc.team7316.util.commands.sensors.PollColor;
import org.firstinspires.ftc.team7316.util.commands.sensors.UpdateVuforia;
import org.firstinspires.ftc.team7316.util.subsystems.JewelArm;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 11/19/17.
 */

@Autonomous(name = "blue team jewel close")
public class BlueJewelClose extends LinearOpMode {
//    private boolean ran = false;
//    @Override
//    public void onInit() {
//        Hardware.instance.vuforiaCameraWrapper.startTracking();
////        Scheduler.instance.add(AutoCodes.closeBlueJewel());
//        Scheduler.instance.add(new UpdateVuforia());
//    }
//
//    @Override
//    public void onLoop() {
//        if(!ran) {
//            MoveJewelArm movearmout = new MoveJewelArm(JewelArm.JewelArmPosition.OUT);
//            PollColor pollColor = new PollColor();
//            WackJewel wackjewel = new WackJewel(Alliance.BLUE);
//            MoveJewelArm movearmin = new MoveJewelArm(JewelArm.JewelArmPosition.IN);
//
//            DriveOffPad offPad = new DriveOffPad(Alliance.BLUE);
//            Wait stop = new Wait(0.5);
//            DriveForTime align = new DriveForTime(Constants.OFF_PAD_POWER, 0, 1);
//            Wait stop2 = new Wait(0.5);
////        TurnUntilKey deteckkey = new TurnUntilKey(-1, -90);
//            DriveDistanceCipher gotocrypto = new DriveDistanceCipher(Alliance.BLUE, DriveDistanceCipher.Position.CLOSE);
//
//            TurnGyroPID turn = new TurnGyroPID(90, 3);
//
//            DriveDistance inchforward = new DriveDistance(Constants.CLOSE_CRYPTO_APPROACH_BLUE, 3);
//            IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
//            DriveDistance backup = new DriveDistance(-4, 3);
//
//            Command[] cmds = {movearmout, pollColor, wackjewel, movearmin, offPad, stop, align, stop2, gotocrypto, turn, inchforward, outtake, backup};
//
//            Command.run(cmds);
//
//            ran = true;
//        }
//
//    }

    @Override
    public void runOpMode() throws InterruptedException {
        Scheduler.instance.clear();
        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);
        Subsystems.createSubsystems();
        Hardware.instance.vuforiaCameraWrapper.startTracking();
        Scheduler.instance.add(new UpdateVuforia());
        waitForStart();

        MoveJewelArm movearmout = new MoveJewelArm(JewelArm.JewelArmPosition.OUT);
        PollColor pollColor = new PollColor();
        WackJewel wackjewel = new WackJewel(Alliance.BLUE);
        MoveJewelArm movearmin = new MoveJewelArm(JewelArm.JewelArmPosition.IN);

        DriveOffPad offPad = new DriveOffPad(Alliance.BLUE);
        Wait stop = new Wait(0.5);
        DriveForTime align = new DriveForTime(Constants.OFF_PAD_POWER, 0, 1);
        Wait stop2 = new Wait(0.5);
//        TurnUntilKey deteckkey = new TurnUntilKey(-1, -90);
        DriveDistanceCipher gotocrypto = new DriveDistanceCipher(Alliance.BLUE, DriveDistanceCipher.Position.CLOSE);

        TurnGyroPID turn = new TurnGyroPID(90, 3);

        DriveDistance inchforward = new DriveDistance(Constants.CLOSE_CRYPTO_APPROACH_BLUE, 3);
        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        DriveDistance backup = new DriveDistance(-4, 3);

        Command[] cmds = {movearmout, pollColor, wackjewel, movearmin, offPad, stop, align, stop2, gotocrypto, turn, inchforward, outtake, backup};

        Command.run(cmds);
    }
}
