package org.firstinspires.ftc.team7316.util.input;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.BlankCommand;

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

        boolean currentValue = super.state();
        if (currentValue && !lastValue) {
            //pid____+++ //dongus
        }
        lastValue = currentValue;

        Hardware.log("this button", pressed);
    }

    @Override
    public boolean state() {
        return pressed;
    }
}