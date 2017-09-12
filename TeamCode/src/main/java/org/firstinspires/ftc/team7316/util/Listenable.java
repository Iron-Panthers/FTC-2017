package org.firstinspires.ftc.team7316.util;

import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;
import org.firstinspires.ftc.team7316.util.input.ButtonListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 11/29/16.
 */
public abstract class Listenable implements Loopable, Conditional {
    private List<ButtonListener> listeners = new ArrayList<>();
    private boolean lastValue = false;

    public void addListener(ButtonListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void loop() {
        boolean currentValue = state();
        subLoop();

        if (currentValue && !lastValue) { // Rising edge
            for (ButtonListener listener: listeners) {
                listener.onPressed();
            }
        } else if (!currentValue && lastValue) { // Falling edge
            for (ButtonListener listener: listeners) {
                listener.onReleased();
            }
        }
        lastValue = currentValue;
    }

    protected abstract void subLoop();
}
