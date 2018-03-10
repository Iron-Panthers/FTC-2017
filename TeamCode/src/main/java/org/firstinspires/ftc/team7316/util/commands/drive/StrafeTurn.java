package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Util;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 1/28/18.
 */

public class StrafeTurn extends Command {

    private boolean clockwise;

    private final double FINISH_THRESHOLD = 5;  //what is accuracy

    private double startAngle, targetAngle;
    private double turnRate, driveRate;

    /**
     * @param targetAngle
     * @param turnRate
     * @param driveRate
     */
    public StrafeTurn(double targetAngle, double turnRate, double driveRate) {
        requires(Subsystems.instance.driveBase);

        this.targetAngle = targetAngle;
        this.turnRate = turnRate;
        this.driveRate = driveRate;
    }

    @Override
    public void init() {
        startAngle = Hardware.instance.gyroWrapper.getHeading();

        clockwise = Util.wrap(targetAngle - startAngle) < 0;
    }

    @Override
    public void loop() {
        //ripped from MecanumDriveBase
        double wantedMovementAngle = Util.wrap(targetAngle - startAngle + (Hardware.instance.gyroWrapper.getHeading() - startAngle));
        wantedMovementAngle *= Math.PI / 180;

        double y = driveRate * Math.cos(wantedMovementAngle);
        double x = driveRate * Math.sin(wantedMovementAngle);

        double fL_bRpower = Constants.SQRT2 * (y + (x-y)/2); //length of the vector
        double fR_bLpower = -Constants.SQRT2 * ((x-y)/2); //again

        Subsystems.instance.driveBase.setMotors(fL_bRpower + turnRate, fR_bLpower - turnRate, fR_bLpower + turnRate, fL_bRpower - turnRate);
    }

    @Override
    public boolean shouldRemove() {
        if(clockwise) {
            return Hardware.instance.gyroWrapper.getHeading() >= targetAngle;
        }
        return Hardware.instance.gyroWrapper.getHeading() <= targetAngle;
    }

    @Override
    protected void end() {
        Subsystems.instance.driveBase.stopMotors();
    }
}
