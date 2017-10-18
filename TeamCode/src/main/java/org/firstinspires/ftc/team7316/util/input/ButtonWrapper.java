package org.firstinspires.ftc.team7316.util.input;

import org.firstinspires.ftc.team7316.util.Listenable;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.flow.DifferentSubsystemSequence;

/**
 * A wrapper for a button.
 * Make sure to call super.loop() if
 */
public class ButtonWrapper extends Listenable {

    private GamepadButton gamepadInput;
    private GamepadWrapper gpSource;

    boolean lastValue; // for detecting rising/falling edges

    public ButtonWrapper(GamepadButton gamepadInput, GamepadWrapper gpSource) {
        this.gamepadInput = gamepadInput;
        this.gpSource  = gpSource;
    }

    @Override
    public void init() {

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
        return gpSource.buttonState(gamepadInput);
    }

}
