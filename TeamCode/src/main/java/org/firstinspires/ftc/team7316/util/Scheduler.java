package org.firstinspires.ftc.team7316.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.scheduler.SingleThreadedScheduler;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

/**
 * Created by andrew on 9/15/16.
 */
public abstract class Scheduler {

    public static boolean inTeleop = true;
    public static final Scheduler instance = new SingleThreadedScheduler();

    public abstract void add(Command command);

    public abstract List<Command> getCommands();

    public abstract void loop();

    public abstract void clear();

}
