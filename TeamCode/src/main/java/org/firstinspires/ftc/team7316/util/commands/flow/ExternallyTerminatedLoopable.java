package org.firstinspires.ftc.team7316.util.commands.flow;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * A loopable that can be terminated externally, not just from within the loopable.
 */
public class ExternallyTerminatedLoopable implements Loopable {

    private Loopable loopable;
    private boolean shouldTerminate;

    public ExternallyTerminatedLoopable(Loopable loopable) {
        this.loopable = loopable;
    }

    @Override
    public void init() {
        loopable.init();
        this.shouldTerminate = false;
    }

    @Override
    public void loop() {
        loopable.loop();
    }

    @Override
    public boolean shouldRemove() {
        return loopable.shouldRemove() || shouldTerminate;
    }

    @Override
    public void terminate() {
        loopable.terminate();
    }

    public void stopLoopable() {
        this.shouldTerminate = true;
    }

}
