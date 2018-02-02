package org.firstinspires.ftc.team7316.util.input;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.BlankCommand;

/**
 * Created by jerry on 1/23/18.
 */

public class SingleButtonPressWrapper extends ButtonWrapper {

    private boolean pressed = false;
    private int index;

    public SingleButtonPressWrapper(GamepadButton gamepadInput, GamepadWrapper gpSource, int index) {
        super(gamepadInput, gpSource);
        this.index = index;
    }

    @Override
    public void init() {
        lastValue = false;
    }

    @Override
    public void loop() {

        boolean currentValue = super.state();
        if (currentValue && !lastValue) {
            Constants.tuneConstants(index);
        }
        lastValue = currentValue;

        Hardware.log("this button", pressed);
    }

    @Override
    public boolean state() {
        return pressed;
    }
}