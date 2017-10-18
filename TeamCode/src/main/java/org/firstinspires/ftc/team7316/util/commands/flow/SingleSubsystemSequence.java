package org.firstinspires.ftc.team7316.util.commands.flow;

import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

/**
 * Created by andrew on 10/17/17.
 */

public class SingleSubsystemSequence implements Command {

    private Command[] cmds;
    private int index = 0;
    private Subsystem subsystem;

    public SingleSubsystemSequence(Command... cmds) {
        this.subsystem = cmds[0].requiredSubystem();
        for (Command cmd : cmds) {
            if (cmd.requiredSubystem() != this.subsystem) {
                throw new IllegalArgumentException();
            }
        }

        this.cmds = cmds;
    }

    @Override
    public Subsystem requiredSubystem() {
        return this.subsystem;
    }

    @Override
    public void init() {
        cmds[index].init();
    }

    @Override
    public void loop() {
        Command cmd =  cmds[index];
        cmd.loop();

        if (cmd.shouldRemove()) {
            cmd.terminate();
            index++;
            cmds[index].init();
        }
    }

    @Override
    public boolean shouldRemove() {
        return index >= cmds.length;
    }

    @Override
    public void terminate() {

    }
}
