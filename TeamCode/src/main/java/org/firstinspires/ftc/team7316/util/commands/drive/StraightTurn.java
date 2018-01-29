package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 1/28/18.
 */

public class StraightTurn extends Command {

    private final double FINISH_THRESHOLD = 5;  //what is accuracy

    private double startAngle, targetAngle;
    private double turnRate, driveRate;

    /**
     * @param targetAngle
     * @param turnRate set to negative to turn left while driving
     * @param driveRate
     */
    public StraightTurn(double targetAngle, double turnRate, double driveRate) {
        requires(Subsystems.instance.driveBase);

        this.targetAngle = targetAngle;
        this.turnRate = turnRate;
        this.driveRate = driveRate;
    }

    @Override
    public void init() {
        startAngle = Hardware.instance.gyroWrapper.getHeading();
    }

    @Override
    public void loop() {
        //ripped from MecanumDriveBase
        double wantedMovementAngle = targetAngle - (Hardware.instance.gyroWrapper.getHeading() - startAngle);
        wantedMovementAngle *= Math.PI / 180;

        double y = driveRate * Math.cos(wantedMovementAngle);
        double x = driveRate * Math.sin(wantedMovementAngle);

        double fL_bRpower = Constants.sqrt2 * (y + (x-y)/2); //length of the vector
        double fR_bLpower = -Constants.sqrt2 * ((x-y)/2); //again

        Subsystems.instance.driveBase.setMotors(fL_bRpower + turnRate, fR_bLpower - turnRate, fR_bLpower + turnRate, fL_bRpower - turnRate);
    }

    @Override
    public boolean shouldRemove() {
        return Math.abs(targetAngle - Hardware.instance.gyroWrapper.getHeading()) < FINISH_THRESHOLD;
    }

    @Override
    protected void end() {
        Subsystems.instance.driveBase.stopMotors();
    }
}
