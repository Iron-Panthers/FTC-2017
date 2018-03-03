package org.firstinspires.ftc.team7316.util.commands.drive.turn;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.CryptoLocations;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by jerry on 2/22/18.
 */

public class TurnGyroCryptoVP extends Command {

    private TurnGyroPID turn;
    private double amount;
    private double fullAmount;

    @Override
    public void init() {
        amount = CryptoLocations.deltaAngleForBox(Hardware.instance.vuforiaCameraWrapper.irY, Hardware.instance.vuforiaCameraWrapper.itZ, Hardware.instance.vuforiaCameraWrapper.itY);
        fullAmount = Hardware.instance.gyroWrapper.getHeading() + amount;
        turn = new TurnGyroPID(Hardware.instance.gyroWrapper.getHeading() + amount, 4);
        turn.init();
    }

    @Override
    public void loop() {
        //turn.loop();
        Hardware.log("turn amount delta", amount);
        Hardware.log("turn amount dest", fullAmount);
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
