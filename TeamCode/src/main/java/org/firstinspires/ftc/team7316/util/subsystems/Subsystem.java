package org.firstinspires.ftc.team7316.util.subsystems;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.*;


/**
 * Created by andrew on 10/17/17.
 */

public abstract class Subsystem implements Loopable {

    private Command currentCmd;

    public abstract Command defaultAutoCommand();
    public abstract Command defaultTeleopCommand();

    public Command getDefaultCommand() {
        if (Scheduler.inTeleop) {
            return defaultTeleopCommand();
        } else {
            return defaultAutoCommand();
        }
    }

    public void setCurrentCmd(Command newCmd) {
        this.currentCmd.terminate();
        this.currentCmd = newCmd;
        this.currentCmd.init();
    }

    @Override
    public void init() {
        currentCmd = getDefaultCommand();
    }

    @Override
    public void loop() {
        currentCmd.loop();

        if (currentCmd.shouldRemove()) {
            setCurrentCmd(getDefaultCommand());
        }
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }
}
