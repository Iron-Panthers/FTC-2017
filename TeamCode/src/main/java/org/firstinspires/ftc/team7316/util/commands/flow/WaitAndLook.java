package org.firstinspires.ftc.team7316.util.commands.flow;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.team7316.util.CryptoLocations;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Once the robot is facing the pictograph, the robot will wait in order for the coordinate numbers to settle down.
 * Also gives a chance for the robot to detect the pictograph if it didn't already.
 * If it still doesn't see it, old code is added back in.
 */

public class WaitAndLook extends Command {

    private ElapsedTime time;
    private double wantedTime;
    private int autoConfig;

    public WaitAndLook(double wantedTime, int autoConfig) {
        this.wantedTime = wantedTime;
        this.autoConfig = autoConfig;
        this.time = new ElapsedTime();
    }

    @Override
    public void init() {
        this.time.reset();
    }

    @Override
    public void loop() {
        Hardware.instance.vuforiaCameraWrapper.update();
    }

    @Override
    public boolean shouldRemove() {
        return time.seconds() > this.wantedTime || Hardware.instance.vuforiaCameraWrapper.currentVuMark != RelicRecoveryVuMark.UNKNOWN;
    }

    @Override
    public void end() {
        //  when no pictograph is seen, clear scheduler of everything and add legacy code
        if(Hardware.instance.vuforiaCameraWrapper.vuMark == RelicRecoveryVuMark.UNKNOWN) {
            Scheduler.instance.wipe();
            Subsystems.instance.resetSubsystems();
            Scheduler.instance.add(AutoCodes.oldAutoForCode(autoConfig));
        }
    }
}
