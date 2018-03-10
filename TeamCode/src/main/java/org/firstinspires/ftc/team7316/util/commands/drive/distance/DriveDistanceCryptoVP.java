package org.firstinspires.ftc.team7316.util.commands.drive.distance;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.CryptoLocations;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.IntWrapper;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by jerry on 2/22/18.
 */

public class DriveDistanceCryptoVP extends Command {

    public IntWrapper distanceWrapper;
    private DriveDistance drive;
    private double distance;

    public DriveDistanceCryptoVP(IntWrapper distance) {
        this.distanceWrapper = distance;
    }

    @Override
    public void init() {
        distance = CryptoLocations.distanceForBox(Hardware.instance.vuforiaCameraWrapper.rY, Hardware.instance.vuforiaCameraWrapper.tZ, Hardware.instance.vuforiaCameraWrapper.tY);
        distanceWrapper.value = -Constants.millimetersToTicks(distance);
        drive = new DriveDistance(Constants.millimetersToTicks(distance), 2);
        drive.init();
    }

    @Override
    public void loop() {
        drive.loop();
        Hardware.log("drive distance total", distance);
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
