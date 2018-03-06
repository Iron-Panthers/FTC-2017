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
    private RelicRecoveryVuMark mark;
    public SetConfigVuforia(RelicRecoveryVuMark mark, int autoCode) {
        this.autoCode = autoCode;
        this.mark = mark;
    }

    @Override
    public void init() {
        if (mark == null) {
            CryptoLocations.setConfig(Hardware.instance.vuforiaCameraWrapper.vuMark, autoCode);
            CryptoLocations.removeLocation(Hardware.instance.vuforiaCameraWrapper.vuMark);
        } else {
            CryptoLocations.setConfig(mark, autoCode);
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
