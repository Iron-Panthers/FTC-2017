package org.firstinspires.ftc.team7316.util.commands;


import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

/**
 * A Loopable with a required subsystem
 */

public interface Command extends Loopable {

    Subsystem requiredSubystem();

}
