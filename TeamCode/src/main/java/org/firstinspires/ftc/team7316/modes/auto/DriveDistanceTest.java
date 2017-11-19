package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.modes.TelopBaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveDistance;

/**
 * Created by jerry on 11/3/17.
 */

@Autonomous(name = "you run this, i kms")
public class DriveDistanceTest extends AutoBaseOpMode {

    @Override
    public void onInit() {
        Scheduler.inTeleop = false;
        Scheduler.instance.add(new DriveDistance(10));
    }

    @Override
    public void onLoop() {
        Hardware.log("flerror", Hardware.instance.frontLeftDriveMotorWrapper.getError());
        Hardware.log("flpower", Hardware.instance.frontLeftDriveMotor.getPower());
        Hardware.log("frpower", Hardware.instance.frontRightDriveMotor.getPower());
        Hardware.log("blpower", Hardware.instance.backLeftDriveMotor.getPower());
        Hardware.log("brpower", Hardware.instance.backRightDriveMotor.getPower());
    }
}
