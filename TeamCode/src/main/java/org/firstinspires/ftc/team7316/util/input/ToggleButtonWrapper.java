package org.firstinspires.ftc.team7316.util.input;

/**
 * Created by andrew on 9/15/16.
 */
public class ToggleButtonWrapper extends ButtonWrapper {

    private boolean value = false;

    public ToggleButtonWrapper(GamepadButton gamepadInput, GamepadWrapper gpSource) {
        super(gamepadInput, gpSource);
    }

    @Override
    public boolean state() {
        return value;
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        //according to ftc forums don't need to debounce

        boolean currentValue = super.state();
        if (currentValue && !lastValue) {
            value = !value;
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
}
