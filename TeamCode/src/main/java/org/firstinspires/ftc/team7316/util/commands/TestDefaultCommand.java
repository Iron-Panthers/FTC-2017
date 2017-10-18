package org.firstinspires.ftc.team7316.util.commands;

/**
 * Created by andrew on 10/18/17.
 */

import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;


public class TestDefaultCommand extends Command {


    int times;

    @Override
    public Subsystem requiredSubystem() {
        return null;
    }

    @Override
    public void onInit() {

    }

    @Override
    public boolean shouldEnd() {
        return false;
    }

    @Override
    public void loop() {
        times++;
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }

    public String toString() {
        return "DFLT" + times;
    }

}
