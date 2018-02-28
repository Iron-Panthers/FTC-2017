package org.firstinspires.ftc.team7316.util.scheduler;

import android.util.Log;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Uses java.util.concurrent.Executor to manage commands.
 */

public class ExecutorScheduler extends Scheduler {

    private static Executor executor = Executors.newCachedThreadPool();

    private List<Command> noLoopCommands = new ArrayList<>();
    private List<Command> commandBuffer = new ArrayList<>();
    private List<Command> loopingCommands = new ArrayList<>();

    public ExecutorScheduler() {

    }

    synchronized void removeLoopingCommand(Command command) {
        this.loopingCommands.remove(command);
    }

    @Override
    public void add(Command command) {
        commandBuffer.add(command);
    }

    @Override
    public List<Command> getCommands() {
        List<Command> commands = new ArrayList<>();
        Collections.addAll(loopingCommands);
        Collections.addAll(noLoopCommands);
        Collections.addAll(commandBuffer);
        return commands;
    }

    @Override
    public void loop() {
        while (commandBuffer.size() > 0) {
            Command cmd = commandBuffer.remove(0);
            switch (cmd.getCommandType()) {
                case NO_LOOP:
                case SINGLE_RUN:
                    noLoopCommands.add(cmd);
                    cmd.init();
                    break;
                case USES_LOOP:
                    executor.execute(new CommandRunnable(this, cmd));
                    loopingCommands.add(cmd);
                    break;
            }
        }
        for (int i = noLoopCommands.size() - 1; i >= 0; i++) {
            Command cmd = noLoopCommands.get(i);
            if (attemptRemoval(cmd)) {
                noLoopCommands.remove(i);
            }
        }
    }

    @Override
    public void clear() {
        commandBuffer.clear();
        noLoopCommands.clear();
        loopingCommands.clear();
    }

    /**
     * Removes the command from this dude if it should remove it.
     * @param cmd the command
     * @return if we removed or not
     */
    boolean attemptRemoval(Command cmd) {
        boolean shouldRemove = cmd.shouldRemove();
        boolean isNotNull = cmd.terminatedListener != null;
        boolean listener = (isNotNull && cmd.terminatedListener.isDone());
        Log.d(Hardware.tag, String.format("Scheduler remove conditions for command %s: %s %s %s", cmd.toString(), shouldRemove, isNotNull, listener));

        if (shouldRemove || listener) {
            //System.out.println(cmd.getClass() + " removed.");
            Log.i(Hardware.tag, "Scheduler removing command " + cmd.toString());

            cmd._end();
            cmd.terminatedListener = null;

            for (Subsystem subsystem : cmd.requiredSubsystems) {
                if (subsystem.needsDefault && subsystem.hasDefault()) {
                    // we set the command to null so that it isn't interrupted.
                    // If we didn't do this, the command we receive "end" and also "interrupt"
                    subsystem.currentCmd = null;

                    Log.i(Hardware.tag, "Scheduler adding default command to subsystem " + subsystem.toString());
                    this.add(subsystem.getDefaultCommand());
                }
            }
            return true;
        }
        return false;
    }
}
