package org.firstinspires.ftc.team7316.util.subsystems;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.commands.*;


/**
 * Created by andrew on 10/17/17.
 */

public interface Subsystem {

    public Command defaultAutoCommand();
    public Command defaultTeleopCommand();

}
