package org.firstinspires.ftc.team7316.util;

import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;
import org.firstinspires.ftc.team7316.util.commands.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 11/29/16.
 */
public abstract class Listenable implements Loopable, Conditional {
    private List<Command> onPressed = new ArrayList<>();
    private List<Command> whileHeld = new ArrayList<>();
    private boolean lastValue = false;

    public void onPressed(Command listener) {
        this.onPressed.add(listener);
    }
    public void whileHeld(Command listener) {
        this.whileHeld.add(listener);
    }

    public void subLoop() {

    }

    @Override
    public void loop() {
        boolean currentValue = state();
        subLoop();

        if (currentValue && !lastValue) { // Rising edge
            for (Command listener: onPressed) {
                listener.start();
            }

            for (Command listener: whileHeld) {
                listener.start();
            }
        }

        if (!currentValue && lastValue) { //falling edge
            for (Command listener: whileHeld) {
                listener.end();
            }
        }

        lastValue = currentValue;
    }

}
