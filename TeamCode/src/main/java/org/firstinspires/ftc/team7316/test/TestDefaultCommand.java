package org.firstinspires.ftc.team7316.test;

import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

/**
 * Created by Maxim on 10/23/2017.
 */

public class TestDefaultCommand extends Command {

    int i;

    public TestDefaultCommand(Subsystem requires) {
        requires(requires);
    }

    @Override
    public void init() {
        i = 0;
    }

    @Override
    public void loop() {
        i++;
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void end() {

    }

    @Override
    public String toString() {
        return String.format("%s(%s)", getClass().getSimpleName(), i);
    }
}
