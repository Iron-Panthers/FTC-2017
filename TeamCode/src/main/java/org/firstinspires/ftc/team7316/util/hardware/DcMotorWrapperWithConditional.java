package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;
import org.firstinspires.ftc.team7316.util.input.AxisWrapper;

/**
 * Created by andrew on 11/19/16.
 */
public class DcMotorWrapperWithConditional extends DcMotorWrapper implements Loopable {

    private Conditional conditional;
    private boolean lastVal;

    public DcMotorWrapperWithConditional(DcMotor motor, AxisWrapper axis, Conditional conditional) { //disables when conditional is true
        super(motor, axis);
        this.conditional = conditional;
    }

    @Override
    public void loop() {
        if (!conditional.state()) {
            super.loop();
        }
        if (conditional.state() && !lastVal) {
            motor.setPower(0);
        }

        lastVal = conditional.state();
    }
}
