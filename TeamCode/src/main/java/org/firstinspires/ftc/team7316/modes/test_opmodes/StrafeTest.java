package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 11/18/17.
 */

public class StrafeTest extends AutoBaseOpMode {
    @Override
    public void onInit() {
        //fl: 536
        //fr: -416
        //bl: -487
        //br: 391
        Scheduler.inTeleop = false;
        Subsystems.instance.driveBase.resetEncoders();
        Hardware.instance.frontLeftDriveMotorWrapper.setTargetTicks(500);
        Hardware.instance.frontRightDriveMotorWrapper.setTargetTicks(-500);
        Hardware.instance.backLeftDriveMotorWrapper.setTargetTicks(-500);
        Hardware.instance.backRightDriveMotorWrapper.setTargetTicks(500);
    }

    @Override
    public void onLoop() {
        Subsystems.instance.driveBase.driveWithPID();
        Hardware.log("flpos", Hardware.instance.frontLeftDriveMotor.getCurrentPosition());
        Hardware.log("frpos", Hardware.instance.frontRightDriveMotor.getCurrentPosition());
        Hardware.log("blpos", Hardware.instance.backLeftDriveMotor.getCurrentPosition());
        Hardware.log("brpos", Hardware.instance.backRightDriveMotor.getCurrentPosition());
    }
}
