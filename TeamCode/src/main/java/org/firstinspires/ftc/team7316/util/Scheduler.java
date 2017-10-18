package org.firstinspires.ftc.team7316.util;

import java.util.ArrayList;
import java.util.HashMap;
import org.firstinspires.ftc.team7316.util.commands.*;

/**
 * Created by andrew on 9/15/16.
 */
public class Scheduler {

    public static final Scheduler instance = new Scheduler();

    private ArrayList<Command> tasks = new ArrayList<Command>();
    private HashMap<Command, Boolean> hasInitialized = new HashMap<>();

    private Scheduler () {}

    public void addTask(Command newTask) {
        int i = 0;
        for (Command task : tasks) {
            if (task.requiredSubystem() == newTask.requiredSubystem()) {
                tasks.remove(i);
                task.terminate();
                hasInitialized.remove(task);
                tasks.add(newTask);
                hasInitialized.put(newTask, true);
                return;
            }
            i++;
        }

        tasks.add(newTask);
        hasInitialized.put(newTask, true);
    }

    public void loop() {
        int i = 0;
        while (i < tasks.size()) {
            Command thisTask = tasks.get(i);
            if (hasInitialized.get(thisTask)) {
                thisTask.init();
                hasInitialized.put(thisTask, false);
            }

            thisTask.loop();
            if (thisTask.shouldRemove()) {
                tasks.remove(i);
                thisTask.terminate();
                hasInitialized.remove(thisTask);
                addTask(thisTask.requiredSubystem().defaultTeleopCommand());
            } else {
                i++;
            }
        }
    }

    public void clear() {
        this.hasInitialized.clear();
        this.tasks.clear();
    }

}
