package org.firstinspires.ftc.team7316.util.subsystems;

import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.*;


/**
 * Created by andrew on 10/17/17.
 */

public abstract class Subsystem {

    public Command currentCmd;
    public boolean needsDefault = true;

    public abstract Command defaultAutoCommand();
    public abstract Command defaultTeleopCommand();

    public boolean hasDefault() {
        return (getDefaultCommand() != null);
    }

    public Command getDefaultCommand() {
        if (Scheduler.inTeleop) {
            return defaultTeleopCommand();
        } else {
            return defaultAutoCommand();
        }
    }

}
