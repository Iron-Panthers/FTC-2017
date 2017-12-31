package org.firstinspires.ftc.team7316.util.commands.sensors;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by jerry on 11/22/17.
 */

public class DetectCipher extends Command {

    private double timeout;
    private ElapsedTime timer;

    public DetectCipher(double timeout) {
        timer = new ElapsedTime();
        this.timeout = timeout;
    }

    @Override
    public void init() {
        timer.reset();
    }

    @Override
    public void loop() {
        Hardware.instance.vuforiaCameraWrapper.update();
    }

    @Override
    public boolean shouldRemove() {
        return Hardware.instance.vuforiaCameraWrapper.vuMark != RelicRecoveryVuMark.UNKNOWN || timer.seconds() >= timeout;
    }

    @Override
    protected void end() {

    }
}
