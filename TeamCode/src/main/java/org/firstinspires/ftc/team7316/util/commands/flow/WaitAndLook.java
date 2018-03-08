package org.firstinspires.ftc.team7316.util.commands.flow;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.*;

/**
 * Created by andrew on 3/7/18.
 */

public class WaitAndLook extends Command {

    private ElapsedTime time;
    private double wantedTime;

    public WaitAndLook(double wantedTime) {
        this.wantedTime = wantedTime;
        this.time = new ElapsedTime();
    }

    @Override
    public void init() {
        this.time.reset();
    }

    @Override
    public void loop() {
        Hardware.instance.vuforiaCameraWrapper.update();
    }

    @Override
    public boolean shouldRemove() {
        return time.seconds() > this.wantedTime;
    }

    @Override
    public void end() {

    }
}
