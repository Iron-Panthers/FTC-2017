package org.firstinspires.ftc.team7316.util.input;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Maxim on 1/31/2017.
 */

public class CapballDropper implements ButtonListener {

    public static final double OPEN = 0.825, CLOSED = 0.5;
    private Servo servo;

    public CapballDropper(Servo servo) {
        this.servo = servo;
        servo.setPosition(CLOSED);
    }

    @Override
    public void onPressed() {
        servo.setPosition(OPEN);
    }

    @Override
    public void onReleased() {

    }
}
