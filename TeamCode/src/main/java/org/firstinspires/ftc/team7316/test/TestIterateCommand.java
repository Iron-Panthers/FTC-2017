package org.firstinspires.ftc.team7316.test;

import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

/**
 * Created by Maxim on 10/23/2017.
 */

public class TestIterateCommand extends Command {

    private final String name;
    private final int times;
    private int i;

    public TestIterateCommand(String name, int times, Subsystem... requires) {
        for (Subsystem s: requires) {
            requires(s);
        }
        this.times = times;
        this.name = name;
    }

    @Override
    public void init() {
        this.i = times;
    }

    @Override
    public void loop() {
        i--;
    }

    @Override
    public boolean shouldRemove() {
        return i <= 0;
    }

    @Override
    public void end() {

    }

    @Override
    public String toString() {
        return String.format("%s(%s:%s)", getClass().getSimpleName(), this.name, this.i);
    }
}
