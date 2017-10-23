package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.input.ButtonWrapper;

/**
 * Created by jerry on 10/11/17.
 * not wayne
 * i totaly did this
 */

public class ServoWrapper implements Loopable {
    private Servo servo;
    private ButtonWrapper button;
    private boolean prevState = false;
    private double on, off, defaultPos;

    public ServoWrapper(Servo servo, ButtonWrapper button, double on, double off) {
        this.servo = servo;
        this.button = button;
        this.on = on;
        this.off = off;
        this.defaultPos = this.off;
    }

    public ServoWrapper(Servo servo, ButtonWrapper button, double on, double off, double defaultPos) {
        this.servo = servo;
        this.button = button;
        this.on = on;
        this.off = off;
        this.defaultPos = defaultPos;
    }

    @Override
    public void init() {
        servo.setPosition(this.defaultPos);
    }

    @Override
    public void loop() {
        if (button.state() != this.prevState) {
            if (button.state()) {
                servo.setPosition(on);
            } else {
                servo.setPosition(off);
            }
        }

        this.prevState = button.state();
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }
}
