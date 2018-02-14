package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 12/30/17.
 */

public class DriveOffPad extends Command {

    private final double FLAT_THRESHOLD = 2; //in degrees whatever
    private double flatAngle;

    private boolean tilted = false;

    private int direction;

    public DriveOffPad(Alliance a) {
        requires(Subsystems.instance.driveBase);
        switch (a) {
            case RED:
                direction = 1;
                break;
            case BLUE:
                direction = -1;
                break;
        }
    }

    @Override
    public void init() {
        flatAngle = Hardware.instance.gyroWrapper.getPitch();
    }

    @Override
    public void loop() {
        if(!isFlat()) {
            tilted = true;
        }
        Subsystems.instance.driveBase.setMotorPowers(Constants.OFF_PAD_POWER * direction); //move to constants and has to go backwards
    }

    @Override
    public boolean shouldRemove() {
        return tilted && isFlat();
    }

    @Override
    protected void end() {
        Subsystems.instance.driveBase.stopMotors();
    }

    private boolean isFlat() {
        return Math.abs(Hardware.instance.gyroWrapper.getPitch() - flatAngle) <= FLAT_THRESHOLD;
    }
}
