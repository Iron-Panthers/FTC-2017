package org.firstinspires.ftc.team7316.util.commands.drive.turn;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Util;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 1/6/18.
 */

public class TurnUntilKey extends Command {

    private final double MAX_SPEED = 0.3;

    private int direction;
    private double angleTimeout;
    private double startAngle;

    /**
     *
     * @param direction 1 for turning right, -1 for turning left
     * @param angleTimeout
     */
    public TurnUntilKey(int direction, double angleTimeout) {
        this.direction = direction;
        this.angleTimeout = Util.wrap(angleTimeout);
    }

    @Override
    public void init() {
        startAngle = Hardware.instance.gyroWrapper.getHeading();
        Subsystems.instance.driveBase.stopMotors();
    }

    @Override
    public void loop() {
        Subsystems.instance.driveBase.turnMotors(MAX_SPEED * direction);
    }

    @Override
    public boolean shouldRemove() {
        if(Hardware.instance.vuforiaCameraWrapper.vuMark != RelicRecoveryVuMark.UNKNOWN) {
            if (direction == 1) {
                return Hardware.instance.gyroWrapper.getHeading() > angleTimeout;
            } else {
                return Hardware.instance.gyroWrapper.getHeading() < angleTimeout;
            }
        }
        return false;
    }

    @Override
    protected void end() {
        Subsystems.instance.driveBase.stopMotors();
        TurnGyroPID turnback = new TurnGyroPID(startAngle, 5);
        turnback.init();
        while(!turnback.shouldRemove()) {
            turnback.loop();
        }
        turnback.end();
    }
}
