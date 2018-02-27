package org.firstinspires.ftc.team7316.util.scheduler;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Manually manages a fixed thread pool. Not complete.
 */
public class ManagedThreadScheduler extends Scheduler {

    private SlaveThread[] slaves;
    //BlockingQueue<Command> queue = new LinkedBlockingDeque<>();

    public ManagedThreadScheduler(int slaves) {
        this.slaves = new SlaveThread[slaves];
        for (int i=0; i < slaves; i++) {
            this.slaves[i] = new SlaveThread(this);
        }
    }

    @Override
    public synchronized void add(Command command) {
        SlaveThread min = slaves[0];
        int minRunning = min.getCurrentlyRunning();

        for (int i=1; i < slaves.length; i++) {
            SlaveThread slave = slaves[i];
            int running = slave.getCurrentlyRunning();
            if (running == 0) {
                min = slave;
                minRunning = running;
                break;
            }
            if (running < minRunning) {
                min = slave;
                minRunning = running;
            }
        }

        min.add(command);
    }

    @Override
    public List<Command> getCommands() {
        List<Command> cmds = new ArrayList<>();
        for (SlaveThread slave: slaves) {
            cmds.addAll(slave.commands);
        }
        return cmds;
    }

    @Override
    public void loop() {

    }

    @Override
    public void clear() {

    }
}
