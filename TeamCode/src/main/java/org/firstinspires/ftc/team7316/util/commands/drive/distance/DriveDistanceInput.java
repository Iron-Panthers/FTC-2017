package org.firstinspires.ftc.team7316.util.commands.drive.distance;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 1/23/18.
 */

public class DriveDistanceInput extends Command {

    private double p,i,d,f;
    private DriveDistance drive;

    public DriveDistanceInput(double p, double i, double d, double f) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
    }

    @Override
    public void init() {
        Subsystems.instance.driveBase.setMotorPID(p, i, d, f);
        drive = new DriveDistance(Constants.inchesToTicks(10));
        drive.init();
    }

    @Override
    public void loop() {
        drive.loop();
    }

    @Override
    public boolean shouldRemove() {
        return drive.shouldRemove();
    }

    @Override
    protected void end() {
        drive.end();
    }
}
