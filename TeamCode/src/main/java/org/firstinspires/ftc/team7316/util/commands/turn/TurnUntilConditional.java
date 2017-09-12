package org.firstinspires.ftc.team7316.util.commands.turn;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;

/**
 * Created by andrew on 11/20/16.
 */
public class TurnUntilConditional extends TurnGyro {

    private Conditional conditional;

    /**
     * @param deltaBearing never input a negative value for this
     * @param power        don't turn in the inefficient direction, it will cause problems (deltaBearing < 180)
     * @param leftMotor
     * @param rightMotor
     * @param gyro
     */
    public TurnUntilConditional(float deltaBearing, double power, DcMotor leftMotor, DcMotor rightMotor, GyroSensor gyro, Conditional conditional) {
        super(deltaBearing, power, leftMotor, rightMotor, gyro);
        this.conditional = conditional;
    }

    @Override
    public boolean shouldRemove() {
        return super.shouldRemove() || conditional.state();
    }


}
