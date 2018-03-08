package org.firstinspires.ftc.team7316.util.commands.drive.turn;

import android.transition.Scene;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.team7316.util.CryptoLocations;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.Util;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 1/6/18.
 */

public class TurnUntilKey extends Command {

//    private final double MAX_SPEED = 0.3;
//
//    private int direction;
//    private double angleTimeout;
//    private double startAngle;
//
//    /**
//     *
//     * @param direction 1 for turning right, -1 for turning left
//     * @param angleTimeout
//     */
//    public TurnUntilKey(int direction, double angleTimeout) {
//        this.direction = direction;
//        this.angleTimeout = Util.wrap(angleTimeout);
//    }
//
//    @Override
//    public void init() {
//        startAngle = Hardware.instance.gyroWrapper.getHeading();
//        Subsystems.instance.driveBase.stopMotors();
//    }
//
//    @Override
//    public void loop() {
//        Subsystems.instance.driveBase.turnMotors(MAX_SPEED * direction);
//    }
//
//    @Override
//    public boolean shouldRemove() {
//        if(Hardware.instance.vuforiaCameraWrapper.vuMark != RelicRecoveryVuMark.UNKNOWN) {
//            if (direction == 1) {
//                return Hardware.instance.gyroWrapper.getHeading() > angleTimeout;
//            } else {
//                return Hardware.instance.gyroWrapper.getHeading() < angleTimeout;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    protected void end() {
//        Subsystems.instance.driveBase.stopMotors();
//        TurnGyroPID turnback = new TurnGyroPID(startAngle, 5);
//        turnback.init();
//        while(!turnback.shouldRemove()) {
//            turnback.loop();
//        }
//        turnback.end();
//    }

    private TurnGyroPID turn;
    private double turnAmount;
    private boolean CLOCKWISE;
    private int autoCode;

    public TurnUntilKey(double guessAngle, int autoCode) {
        this.turnAmount = guessAngle;
        this.autoCode = autoCode;
    }

    @Override
    public void init() {
        CLOCKWISE = Util.wrap(turnAmount - Hardware.instance.gyroWrapper.getHeading()) > 0;

        turn = new TurnGyroPID(turnAmount, 2.5);
        turn.init();
        Hardware.instance.vuforiaCameraWrapper.update();
    }

    @Override
    public void loop() {
        turn.loop();
        Hardware.instance.vuforiaCameraWrapper.update();
    }

    @Override
    public boolean shouldRemove() {
        // 2000 ms / 75 ms is 27 ish
        // 75 ms is the average dT
        return Hardware.instance.vuforiaCameraWrapper.currentVuMark != RelicRecoveryVuMark.UNKNOWN || turn.shouldRemove();
    }

    @Override
    protected void end() {
        if (Hardware.instance.vuforiaCameraWrapper.vuMark == RelicRecoveryVuMark.UNKNOWN) {
            Scheduler.instance.clear();
            Scheduler.instance.addDefaultCommands();

            //Scheduler.instance.add(AutoCodes.oldAutoForCode(autoCode));
        }
        turn.interrupt();
    }

}
