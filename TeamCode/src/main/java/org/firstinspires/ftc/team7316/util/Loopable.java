package org.firstinspires.ftc.team7316.util;

/**
 * Created by andrew on 9/15/16.
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
