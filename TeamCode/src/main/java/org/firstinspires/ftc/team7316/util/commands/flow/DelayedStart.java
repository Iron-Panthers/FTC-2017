package org.firstinspires.ftc.team7316.util.commands.flow;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by Maxim on 10/24/2016.
 */
public class DelayedStart implements Loopable {

    Runnable callback;
    ElapsedTime timer;
    double delay;

    /**
     * Create a DelayedStart loopable
     * @param delay in seconds
     * @param callback the callback to run
     */
    public DelayedStart(double delay, Runnable callback) {
        this.callback = callback;
        this.timer = new ElapsedTime();
        this.delay = delay;
    }

    @Override
    public void init() {
        timer.reset();
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return timer.seconds() >= delay;
    }

    @Override
    public void terminate() {
        callback.run();
    }
}
