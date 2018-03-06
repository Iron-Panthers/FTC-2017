package org.firstinspires.ftc.team7316.util.commands.sensors;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.team7316.util.CryptoLocations;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.*;

/**
 * Created by andrew on 3/5/18.
 */

public class SetConfigVuforia extends Command {

    private int autoCode;
    private boolean keyMark;
    public SetConfigVuforia(boolean keyMark, int autoCode) {
        this.autoCode = autoCode;
        this.keyMark = keyMark;
    }

    @Override
    public void init() {
        if (keyMark) {
            CryptoLocations.setConfig(Hardware.instance.vuforiaCameraWrapper.vuMark, autoCode);
            CryptoLocations.removeLocation(Hardware.instance.vuforiaCameraWrapper.vuMark);
        } else {
            CryptoLocations.setConfig(CryptoLocations.popLocation(), autoCode);
        }
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return true;
    }

    @Override
    protected void end() {

    }
}
