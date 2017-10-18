package org.firstinspires.ftc.team7316.util;

/**
 * Goes in a scheduler
 */
public interface Loopable {

    /**
     * Do jack shift and die
     */
    Loopable BLANK = new Loopable() {

        @Override
        public void init() {

        }

        @Override
        public void loop() {

        }

        @Override
        public boolean shouldRemove() {
            return true;
        }

        @Override
        public void terminate() {

        }

    };

    void init();
    void loop();
    boolean shouldRemove();
    void terminate();

}
