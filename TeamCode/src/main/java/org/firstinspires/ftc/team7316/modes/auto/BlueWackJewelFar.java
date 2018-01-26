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
 * Created by jerry on 10/28/17.
 */

@Autonomous(name = "blue team jewel far")
public class BlueWackJewelFar extends LinearOpMode {

//    private boolean ran = false;
//    @Override
//    public void onInit() {
//        Hardware.instance.vuforiaCameraWrapper.startTracking();
////        Scheduler.instance.add(AutoCodes.farBlueJewel());
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
////        TurnUntilKey detectkey = new TurnUntilKey(-1, -90);
//            DriveDistance backward = new DriveDistance(-Constants.FAR_CRYPTO_DISTANCE, 4);
//
//            TurnGyroPID turnleft = new TurnGyroPID(-90, 3);
//            DriveDistanceCipher gotocrypto = new DriveDistanceCipher(Alliance.BLUE, DriveDistanceCipher.Position.FAR);
//            TurnGyroPID turnleft2 = new TurnGyroPID(-90, 3);
//
//            IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
//            DriveDistance ram = new DriveDistance(4, 4);
//            DriveDistance backup = new DriveDistance(-4, 4);
//
//            Command[] cmds = {movearmout, pollColor, wackjewel, movearmin, offPad, stop, align, stop2, backward, turnleft, gotocrypto, turnleft2, outtake, ram, backup};
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
//        TurnUntilKey detectkey = new TurnUntilKey(-1, -90);
        DriveDistance backward = new DriveDistance(-Constants.FAR_CRYPTO_DISTANCE, 4);

        TurnGyroPID turnleft = new TurnGyroPID(-90, 3);
        DriveDistanceCipher gotocrypto = new DriveDistanceCipher(Alliance.BLUE, DriveDistanceCipher.Position.FAR);
        TurnGyroPID turnleft2 = new TurnGyroPID(-90, 3);

        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME);
        DriveDistance ram = new DriveDistance(4, 4);
        DriveDistance backup = new DriveDistance(-4, 4);

        Command[] cmds = {movearmout, pollColor, wackjewel, movearmin, offPad, stop, align, stop2, backward, turnleft, gotocrypto, turnleft2, outtake, ram, backup};
        Command.run(cmds);
    }
}