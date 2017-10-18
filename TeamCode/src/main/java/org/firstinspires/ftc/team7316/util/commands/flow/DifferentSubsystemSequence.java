package org.firstinspires.ftc.team7316.util.commands.flow;

import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by andrew on 10/17/17.
 */

public class DifferentSubsystemSequence implements Loopable {

    private Command[] cmds;
    private int index = 0;

    public DifferentSubsystemSequence(Command... cmds) {
        for (int i = 1; i < cmds.length; i++) {
            if (cmds[i].requiredSubystem() == cmds[i-1].requiredSubystem()) {
                throw new IllegalArgumentException();
            }
        }

        this.cmds = cmds;
    }

    @Override
    public void init() {
        cmds[index].requiredSubystem().setCurrentCmd(cmds[index]);
    }

    @Override
    public void loop() {
        if (cmds[index].shouldRemove()) {
            index++;
            cmds[index].start();
        }
    }

    @Override
    public boolean shouldRemove() {
        return index >= cmds.length;
    }

    @Override
    public void terminate() {

    }

    public static DifferentSubsystemSequence sequenceFromCommands(Command... inputCmds) {
        ArrayList<ArrayList<Command>> groupedCmds = new ArrayList<>();
        int lastIndex = -1;
        Command[] cmds;

        Subsystem lastSubsystem = null;
        for (Command cmd : inputCmds) {
            if (lastSubsystem == cmd.requiredSubystem()) {
                groupedCmds.get(lastIndex).add(cmd);
            } else {
                ArrayList<Command> newGroup = new ArrayList<>();
                newGroup.add(cmd);
                groupedCmds.add(newGroup);
                lastIndex++;
            }
        }

        cmds = new Command[groupedCmds.size()];
        int i = 0;
        for (ArrayList<Command> group : groupedCmds) {
            if (group.size() == 1) {
                cmds[i] = group.get(0);
            } else {
                cmds[i] = new SingleSubsystemSequence(new Command[group.size()]);
            }
            i++;
        }

        return new DifferentSubsystemSequence(cmds);
    }
}
