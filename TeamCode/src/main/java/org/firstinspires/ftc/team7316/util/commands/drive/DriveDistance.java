package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 10/28/17.
 */

public class DriveDistance extends Command {

    private int distance; //currently in encoder ticks

    public DriveDistance(int distance) {
        requires(Subsystems.instance.driveBase);
        this.distance = distance;
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {

    }
}
