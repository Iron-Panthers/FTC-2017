package org.firstinspires.ftc.team7316.util.commands.drive.turn;

import android.util.Log;

import org.firstinspires.ftc.team7316.util.CryptoLocations;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by jerry on 2/22/18.
 */

public class TurnGyroCryptoVP extends Command {

    private TurnGyroPID turn;
    private double amount;

    @Override
    public void init() {
        amount = CryptoLocations.angleForBox(Hardware.instance.vuforiaCameraWrapper.rY, Hardware.instance.vuforiaCameraWrapper.tZ, Hardware.instance.vuforiaCameraWrapper.tY);
        turn = new TurnGyroPID(amount, 2);
        turn.init();

        Log.d("turn amount delta", String.valueOf(amount));
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
        turn.interrupt();
    }
}
