package org.firstinspires.ftc.team7316.util.input;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Listenable;
import org.firstinspires.ftc.team7316.util.Hardware;

/**
 * Created by Maxim on 1/31/2017.
 */
public class DoubleTap extends Listenable implements ButtonListener {

    private ElapsedTime timer;
    private double delay;

    private boolean secondPress = false;

    public DoubleTap(double delay) {
        timer = new ElapsedTime();
        this.delay = delay;
    }

    @Override
    public void init() {
        secondPress = false;
    }

    @Override
    protected void subLoop() {
        Hardware.log("timer", timer.seconds() < delay);
        Hardware.log("state", this.state());
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }

    @Override
    public boolean state() {
        return secondPress;
    }

    @Override
    public void onPressed() {
        if (timer.seconds() <= delay) {
            this.secondPress = true;
        } else {
            timer.reset();
            this.secondPress = false;
        }

        /*if (timer.seconds() <= delay) {
            state = true;
        } else {
            timer.reset();
        }*/
    }

    @Override
    public void onReleased() {
    }

}
