package org.firstinspires.ftc.team7316.util.commands;


import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

/**
 * Created by andrew on 10/17/17.
 */

public interface Command extends Loopable {

    public Subsystem requiredSubystem();

}
