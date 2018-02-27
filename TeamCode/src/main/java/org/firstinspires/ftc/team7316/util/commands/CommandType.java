package org.firstinspires.ftc.team7316.util.commands;

/**
 * Created by maxim on 2/27/18.
 */

public enum CommandType {
    /**
     * It uses the full lifecycle: init, loop, and end.
     */
    USES_LOOP,

    /**
     * It only uses init and end.
     */
    NO_LOOP,

    /**
     * It just goes through init and will always return true in shouldRemove.
     */
    SINGLE_RUN
}
