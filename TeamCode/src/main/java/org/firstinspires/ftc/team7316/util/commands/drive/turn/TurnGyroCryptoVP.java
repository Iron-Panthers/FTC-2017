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

    @Override
    public void init() {
        turn = new TurnGyroPID(CryptoLocations.deltaAngleForBox(Hardware.instance.vuforiaCameraWrapper.irY, Hardware.instance.vuforiaCameraWrapper.itX, Hardware.instance.vuforiaCameraWrapper.itZ), 3);
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
        turn.interrupt();
    }
}
