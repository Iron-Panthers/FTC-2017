package org.firstinspires.ftc.team7316.test;

import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

/**
 * Created by Maxim on 10/23/2017.
 */

public class TestSubsystem extends Subsystem {

    @Override
    public void reset() {

    }

    @Override
    public Command defaultAutoCommand() {
        return new TestDefaultCommand(this);
    }

    @Override
    public Command defaultTeleopCommand() {
        return null;
    }
}
