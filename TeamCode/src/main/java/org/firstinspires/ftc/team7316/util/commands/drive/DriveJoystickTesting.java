package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.input.GamepadAxis;
import org.firstinspires.ftc.team7316.util.input.OI;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 11/3/17.
 */

public class DriveJoystickTesting extends Command {
    @Override
    public void init() {
        requires(Subsystems.instance.driveBase);
    }

    @Override
    public void loop() {
        double lX = OI.instance.gp1.axisValue(GamepadAxis.L_STICK_X);
        if (Math.abs(lX) < Constants.JOYSTICK_DRIVE_DEADZONE) {
            lX = 0;
        }

        double lY = OI.instance.gp1.axisValue(GamepadAxis.L_STICK_Y);
        if (Math.abs(lY) < Constants.JOYSTICK_DRIVE_DEADZONE) {
            lY = 0;
        }

        double rX = OI.instance.gp1.axisValue(GamepadAxis.R_STICK_X);
        if (Math.abs(rX) < Constants.JOYSTICK_DRIVE_DEADZONE) {
            rX = 0;
        }
        double rY = OI.instance.gp1.axisValue(GamepadAxis.R_STICK_Y);
        if (Math.abs(rY) < Constants.JOYSTICK_DRIVE_DEADZONE) {
            rY = 0;
        }

        Hardware.instance.frontLeftDriveMotor.setPower(lY);
        Hardware.instance.backLeftDriveMotor.setPower(lX);
        Hardware.instance.frontRightDriveMotor.setPower(rY);
        Hardware.instance.backRightDriveMotor.setPower(rX);
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {

    }
}
