package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistance;

/**
 * Created by jerry on 11/3/17.
 */

@Autonomous(name = "DriveDistanceTest")
public class DriveDistanceTest extends AutoBaseOpMode {

    @Override
    public void onInit() {
        Scheduler.inTeleop = false;
        Scheduler.instance.add(new DriveDistance(Constants.inchesToTicks(30)));
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
