package org.firstinspires.ftc.team7316.util.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Util;

/**
 * Drive a motor for an amount of time. Increase power linearly over time between starting and ending.
 */
public class DriveMotorAccelerate implements Loopable {

    private DcMotor motor;
    private ElapsedTime elapsedTime;
    private double startingPower, endingPower, time;

    public DriveMotorAccelerate(DcMotor motor, double startingPower, double endingPower, double time) {
        this.motor = motor;
        this.startingPower = startingPower;
        this.endingPower = endingPower;
        this.time = time;
        this.elapsedTime = new ElapsedTime();
    }

    @Override
    public void init() {
        elapsedTime.reset();
        motor.setPower(startingPower);
    }

    @Override
    public void loop() {
        motor.setPower(Util.map(elapsedTime.seconds(), 0, time, startingPower, endingPower));
    }

    @Override
    public boolean shouldRemove() {
        return elapsedTime.seconds() >= time;
    }

    @Override
    public void terminate() {
        motor.setPower(0);
    }
}
