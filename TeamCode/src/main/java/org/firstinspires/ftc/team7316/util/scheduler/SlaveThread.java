package org.firstinspires.ftc.team7316.util.scheduler;

import android.util.Log;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxim on 2/26/18.
 */

public class SlaveThread extends Thread {

    List<Command> commands = new ArrayList<>();

    private ManagedThreadScheduler parent;

    SlaveThread(ManagedThreadScheduler parent) {
        this.parent = parent;
    }

    @Override
    public void run() {
        for (int i = commands.size() - 1; i >= 0; i--) {
            Command cmd = commands.get(i);
            cmd.loop();

            boolean shouldRemove = cmd.shouldRemove();
            boolean isNotNull = cmd.terminatedListener != null;
            boolean listener = (isNotNull && cmd.terminatedListener.isDone());
            //Log.d(Hardware.tag, String.format("Scheduler remove conditions for command %s: %s %s %s", cmd.toString(), shouldRemove, isNotNull, listener));

            if (shouldRemove || listener) {
                commands.remove(i);

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
                        parent.add(subsystem.getDefaultCommand());
                    }
                }
            }
        }
    }

    public int getCurrentlyRunning() {
        return commands.size();
    }

    public void add(Command command) {
        this.commands.add(command);
    }

}
