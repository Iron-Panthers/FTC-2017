package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.modes.LinearAutoOpMode;
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
 * Created by jerry on 11/18/17.
 */

@Autonomous(name = "red team jewel far")
public class RedWackJewelFar extends LinearAutoOpMode {

//    @Override
//    public void onInit() {
//        Hardware.instance.vuforiaCameraWrapper.startTracking();
//        Scheduler.instance.add(AutoCodes.farRedJewel());
//        Scheduler.instance.add(new UpdateVuforia());
//    }
//
//    @Override
//    public void onLoop() {
//
//    }

    @Override
    public Command[] createCommands() {
        MoveJewelArm movearmout = new MoveJewelArm(JewelArm.JewelArmPosition.OUT);
        PollColor pollColor = new PollColor();
        WackJewel wackjewel = new WackJewel(Alliance.RED);
        MoveJewelArm movearmin = new MoveJewelArm(JewelArm.JewelArmPosition.IN);

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
        IntakeForTime outtake = new IntakeForTime(Constants.OUTTAKE_POWER, Constants.OUTTAKE_TIME); //add to constants later
        DriveForTime ram = new DriveForTime(0.3, 0, 1);
        DriveDistance backup = new DriveDistance(-Constants.FAR_CRYPTO_APPROACH_RED, 2);

        Command[] cmds = {movearmout, pollColor, wackjewel, movearmin, offPad, stop, align, stop2, forward, turnleft, gotocrypto, turnright, approach, outtake, ram, backup};
        return cmds;
    }
}
