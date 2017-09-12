package org.firstinspires.ftc.team7316.util.input;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.flow.ExternallyTerminatedLoopable;

/**
 * A two button toggle that can be extended
 */
public class TwoButtonToggleExtendable implements Loopable {

    ExternallyTerminatedLoopable loop1, loop2, current;
    ButtonWrapper button1, button2;

    /**
     * Construct a TwoButtonToggleExtendable
     * @param button1 The first button
     * @param button2 The second button
     * @param loop1 The Loopable to run when the first button is pressed
     * @param loop2 The Loopable to run when the second button is pressed
     */
    public TwoButtonToggleExtendable(ButtonWrapper button1, ButtonWrapper button2, Loopable loop1, Loopable loop2) {
        this.loop1 = new ExternallyTerminatedLoopable(loop1);
        this.loop2 = new ExternallyTerminatedLoopable(loop2);
        this.button1 = button1;
        this.button2 = button2;
        this.current = null;
    }

    @Override
    public void init() {

        button1.addListener(new ButtonListener() {
            @Override
            public void onPressed() {
                if (current == loop1) {
                    current.stopLoopable();
                    current = null;
                } else {
                    current = loop1;
                    Scheduler.instance.addTask(loop1);
                }
            }

            @Override
            public void onReleased() {

            }
        });

        button2.addListener(new ButtonListener() {
            @Override
            public void onPressed() {

                if (current == loop2) {
                    current.stopLoopable();
                    current = null;
                } else {
                    current = loop2;
                    Scheduler.instance.addTask(loop2);
                }

            }

            @Override
            public void onReleased() {

            }
        });

    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }
}
