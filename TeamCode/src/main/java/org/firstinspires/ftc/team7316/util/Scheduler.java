package org.firstinspires.ftc.team7316.util;

import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.scheduler.ExecutorScheduler;
import org.firstinspires.ftc.team7316.util.scheduler.SingleThreadedScheduler;

import java.util.List;

/**
 * Created by andrew on 9/15/16.
 */
public abstract class Scheduler {

    public static boolean inTeleop = true;
    public static Scheduler instance;

    public static void initialize() {
        instance = new ExecutorScheduler();
    }

    public abstract void add(Command command);

    public abstract List<Command> getCommands();

    public abstract void loop();

    public abstract void clear();

}
