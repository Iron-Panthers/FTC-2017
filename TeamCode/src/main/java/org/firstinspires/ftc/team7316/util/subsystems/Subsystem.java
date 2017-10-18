package org.firstinspires.ftc.team7316.util.subsystems;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by andrew on 10/17/17.
 */

public interface Subsystem {

    public Loopable defaultAutoCommand();
    public Loopable defaultTeleopCommand();

}
