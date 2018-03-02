package org.firstinspires.ftc.team7316.util.commands.drive.distance;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.CryptoLocations;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by jerry on 2/22/18.
 */

public class DriveDistanceCryptoVP extends Command {

    private DriveDistance drive;
    private double distance;

    @Override
    public void init() {
        drive = new DriveDistance(Constants.millimetersToTicks(CryptoLocations.distanceForBox(Hardware.instance.vuforiaCameraWrapper.irY, Hardware.instance.vuforiaCameraWrapper.itZ, Hardware.instance.vuforiaCameraWrapper.itX)), 3);
        drive.init();
        distance = CryptoLocations.distanceForBox(Hardware.instance.vuforiaCameraWrapper.irY, Hardware.instance.vuforiaCameraWrapper.itZ, Hardware.instance.vuforiaCameraWrapper.itX);
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
        drive.interrupt();
    }
}
