package org.firstinspires.ftc.team7316.util.commands.drive;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 11/15/17.
 */

public class TurningWheels extends Command {
    @Override
    public void init() {
        requires(Subsystems.instance.driveBase);
        Subsystems.instance.driveBase.resetEncoders();
        Subsystems.instance.driveBase.setBrakeMode(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    @Override
    public void loop() {
        Hardware.log("frontleft position", Hardware.instance.frontLeftDriveMotor.getCurrentPosition());
        Hardware.log("frontright position", Hardware.instance.frontRightDriveMotor.getCurrentPosition());
        Hardware.log("backleft position", Hardware.instance.backLeftDriveMotor.getCurrentPosition());
        Hardware.log("backright position", Hardware.instance.backRightDriveMotor.getCurrentPosition());
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {

    }
}
