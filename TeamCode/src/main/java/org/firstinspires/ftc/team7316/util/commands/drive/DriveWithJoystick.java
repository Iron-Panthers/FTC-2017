package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.input.GamepadAxis;
import org.firstinspires.ftc.team7316.util.input.OI;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by andrew on 10/18/17.
 */

public class DriveWithJoystick extends Command {

    public DriveWithJoystick() {
        requires(Subsystems.instance.driveBase);
    }

    @Override
    public void init() {
        Subsystems.instance.driveBase.setWantedTurnSpeed(0);
        Subsystems.instance.driveBase.setWantedSpeedAndMovementAngle(0, 0);
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
        Hardware.log("rX", rX);
        Hardware.log("lX", lX);
        Hardware.log("lY", lY);

        //forward/backward deadzone 0.3

        double turnSpeed = rX;
        double moveDir = Math.atan2(lX, lY);
        double moveSpeed = Math.sqrt(lX*lX + lY*lY);

        Subsystems.instance.driveBase.setWantedTurnSpeed(turnSpeed);
        Subsystems.instance.driveBase.setWantedSpeedAndMovementAngle(moveSpeed, moveDir);
        Subsystems.instance.driveBase.driveWithSpeeds();
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void end() {
        Subsystems.instance.driveBase.setWantedTurnSpeed(0);
        Subsystems.instance.driveBase.setWantedSpeedAndMovementAngle(0, 0);
    }
}
