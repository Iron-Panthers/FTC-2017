package org.firstinspires.ftc.team7316.util.input;

import org.firstinspires.ftc.team7316.util.Listenable;
import org.firstinspires.ftc.team7316.util.Scheduler;

/**
 * A wrapper for a button.
 * Make sure to call super.loop() if
 */
public class ButtonWrapper extends Listenable {

    private GamepadButton gamepadInput;
    private GamepadWrapper gpSource;

    protected boolean addedToScheduler = false;

    private boolean toggledValue = false;
    private boolean buttonHasBeenTouched = false;

    boolean lastValue; // for detecting rising/falling edges

    public ButtonWrapper(GamepadButton gamepadInput, GamepadWrapper gpSource) {
        this.gamepadInput = gamepadInput;
        this.gpSource  = gpSource;
    }

    @Override
    public void init() {

    }

    @Override
    public void subLoop() {
        // Deals with the toggledState function
        boolean currentValue = state();
        if (currentValue && !lastValue) {
            toggledValue = !toggledValue;
        }
        lastValue = currentValue;
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void end() {

    }

    @Override
    public boolean state() {
        return gpSource.buttonState(gamepadInput);
    }

    public boolean pressedState() {
        addToScheduler();
        return state();
    }

    public boolean toggledState() {
        addToScheduler();
        return toggledValue;
    }

    public boolean singlePressedState() {
        addToScheduler();
        if(state() && !buttonHasBeenTouched) {
            buttonHasBeenTouched = true;
            return true;
        }
        return false;
    }

    /**
     * This button adds itself to the scheduler on call.
     * Used so that unused buttons aren't added to the scheduler for efficiency.
     * Call this on any method that retrieves state so it knows when to add itself to scheduler.
     */
    protected void addToScheduler() {
        if(!addedToScheduler) {
            Scheduler.instance.add(this);
            addedToScheduler = true;
        }
    }
}
