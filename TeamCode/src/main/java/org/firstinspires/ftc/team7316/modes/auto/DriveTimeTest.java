package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 11/27/17.
 */

@Autonomous(name = "drivetimetesting")
public class DriveTimeTest extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Subsystems.instance.driveBase.resetEncoders();
        Scheduler.instance.add(AutoCodes.driveTimeTesting());
    }

    @Override
    public void onLoop() {
        Hardware.log("fL distance", Hardware.instance.frontLeftDriveMotor.getCurrentPosition());
        Hardware.log("fR distance", Hardware.instance.frontRightDriveMotor.getCurrentPosition());
    }
}