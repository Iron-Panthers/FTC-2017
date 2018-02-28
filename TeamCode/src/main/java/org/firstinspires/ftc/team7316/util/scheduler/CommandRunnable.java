package org.firstinspires.ftc.team7316.util.scheduler;

import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by maxim on 2/27/18.
 */

public class CommandRunnable implements Runnable {

    private final Command command;
    private final ExecutorScheduler parent;

    CommandRunnable(ExecutorScheduler parent, Command command) {
        this.parent = parent;
        this.command = command;
    }

    @Override
    public void run() {
        command.init();
        do {
            command.loop();
        } while (!parent.attemptRemoval(command));
        command._end();
        parent.removeLoopingCommand(command);
    }

}
