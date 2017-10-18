package org.firstinspires.ftc.team7316.util.commands.flow;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.commands.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by andrew on 11/2/16.
 */
public class SimultaneousCommands implements Loopable {

    private Command[] cmds;

    public SimultaneousCommands(Command... cmds) {
        this.cmds = cmds;
    }

    @Override
    public void init() {
        for (Command cmd : this.cmds) {
            cmd.requiredSubystem().setCurrentCmd(cmd);
        }
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        for (Command cmd : cmds) {
            if (!cmd.shouldRemove()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void terminate() {

    }
}
