package org.firstinspires.ftc.team7316.util.input;

import org.firstinspires.ftc.team7316.util.Hardware;

/**
 * Created by jerry on 1/23/18.
 */

public class SingleButtonPressWrapper extends ButtonWrapper {

    private boolean pressed = false;

    public SingleButtonPressWrapper(GamepadButton gamepadInput, GamepadWrapper gpSource) {
        super(gamepadInput, gpSource);
    }

    @Override
    public void init() {
        lastValue = false;
    }

    @Override
    public void loop() {
        if(super.state() && !lastValue) {
            pressed = true;
            lastValue = true;
        }
        else if(super.state() && lastValue) {
            pressed = false;
        }
        else {
            pressed = false;
            lastValue = false;
        }
        Hardware.log("this button", pressed);
    }

    @Override
    public boolean state() {
        return pressed;
    }
}