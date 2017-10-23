package org.firstinspires.ftc.team7316.util;

import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;
import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.commands.flow.WhileHeldWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 11/29/16.
 */
public abstract class Listenable extends Command implements Conditional {
    private List<Command> onPressed = new ArrayList<>();
    private List<WhileHeldWrapper> whileHeld = new ArrayList<>();
    private boolean lastValue = false;

    public void onPressed(Command listener) {
        this.onPressed.add(listener);
    }
    public void whileHeld(WhileHeldWrapper listener) {
        this.whileHeld.add(listener);
    }

    public void subLoop() {

    }

    @Override
    public void loop() {
        boolean currentValue = state();
        subLoop();

        if (currentValue && !lastValue) { // Rising edge
            for (Command listener : onPressed) {
                Scheduler.instance.add(listener);
            }

            for (WhileHeldWrapper listener : whileHeld) {
                Scheduler.instance.add(listener);
            }
        }

        if (!currentValue && lastValue) { //falling edge
            for (WhileHeldWrapper listener: whileHeld) {
                listener.needsEnd = true;
            }
        }

        lastValue = currentValue;
    }

}
