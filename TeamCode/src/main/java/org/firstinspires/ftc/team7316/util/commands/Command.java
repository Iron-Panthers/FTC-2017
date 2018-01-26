package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

import java.util.ArrayList;

/**
 * A Command with a required subsystem
 */

public abstract class Command {

    public TerminatedListener terminatedListener = null;
    public ArrayList<Subsystem> requiredSubsystems = new ArrayList<>();

    private boolean shouldReplace = true;
    public boolean isShouldReplace() {
        return shouldReplace;
    }
    public void setShouldReplace(boolean shouldReplace) {
        this.shouldReplace = shouldReplace;
    }

    public void requires(Subsystem subsystem) {
        if (!requiredSubsystems.contains(subsystem)) { // just to be sure duplicates don't happen
            requiredSubsystems.add(subsystem);
        }
    }

    public abstract void init();
    public abstract void loop();
    public abstract boolean shouldRemove();

    public void _end() {
        if (terminatedListener != null) {
            terminatedListener.onTerminated(this);
        }
        end();
    }
    protected abstract void end();
    public void interrupt() {
        this.end();
    }

}
