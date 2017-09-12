package org.firstinspires.ftc.team7316.util.commands.flow;

import org.firstinspires.ftc.team7316.util.Loopable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by andrew on 11/2/16.
 */
public class SimultaneousCommands implements Loopable {

    private ArrayList<Loopable> cmds;

    public SimultaneousCommands(Loopable... cmds) {
        this.cmds = new ArrayList<Loopable>(Arrays.asList(cmds));
    }

    @Override
    public void init() {
        for (Loopable cmd : this.cmds) {
            cmd.init();
        }
    }

    @Override
    public void loop() {
        for (int i = cmds.size()-1; i >= 0; i--) {
            Loopable cmd = cmds.get(i);
            cmd.loop();

            if (cmd.shouldRemove()) {
                cmd.terminate();
                cmds.remove(i);
            }
        }
    }

    @Override
    public boolean shouldRemove() {
        return cmds.size() <= 0;
    }

    @Override
    public void terminate() {

    }
}
