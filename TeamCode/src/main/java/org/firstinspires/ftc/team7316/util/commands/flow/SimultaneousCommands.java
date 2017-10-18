package org.firstinspires.ftc.team7316.util.commands.flow;

import android.telecom.Call;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.commands.Command;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by andrew on 11/2/16.
 */
public class SimultaneousCommands implements Loopable {

    private CallbackCommand[] cmds;
    private int completed;
    private final EndCondition endCondition;

    public SimultaneousCommands(Command... cmds) {
        this(EndCondition.ALL, cmds);
    }

    public SimultaneousCommands(EndCondition endCondition, Command... cmds) {
        this.endCondition = endCondition;
        this.cmds = new CallbackCommand[cmds.length];
        for (int i=0; i < cmds.length; i++) {
            this.cmds[i] = new CallbackCommand(cmds[i], new Runnable() {
                @Override
                public void run() {
                    completed++;
                }
            });
        }
    }

    @Override
    public void init() {
        for (CallbackCommand cmd : this.cmds) {
            cmd.command.start();
        }
    }

    @Override
    public void loop() {
    }

    @Override
    public boolean shouldRemove() {
        switch (endCondition) {
            case ALL: return completed >= cmds.length;
            case ANY: return completed >= 1;
        }
        return true;  // you dun goofed
    }

    @Override
    public void terminate() {
        if (this.endCondition == EndCondition.ANY) {
            for (CallbackCommand c: cmds) {
                if (!c.getTerminated()) {
                    c.command.terminate();
                }
            }
        }

    }

    public enum EndCondition {
        ALL, ANY
    }
}
