package org.firstinspires.ftc.team7316.util.commands.drive.turn;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistance;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by andrew on 1/31/18.
 */

public class TurnGyroInput extends Command {

    private double p,i,d,f;
    private TurnGyroPID turn;

    public TurnGyroInput(double p, double i, double d, double f) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
    }

    @Override
    public void init() {
        Constants.GYRO_P = p;
        Constants.GYRO_I = i;
        Constants.GYRO_D = d;
        Constants.GYRO_F = f;

        turn = new TurnGyroPID(90);
        turn.init();
    }

    @Override
    public void loop() {
        turn.loop();
    }

    @Override
    public boolean shouldRemove() {
        return turn.shouldRemove();
    }

    @Override
    protected void end() {
        turn.end();
    }
}

