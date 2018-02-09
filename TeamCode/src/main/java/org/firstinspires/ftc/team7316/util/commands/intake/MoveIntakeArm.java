package org.firstinspires.ftc.team7316.util.commands.intake;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 11/18/17. sawn by chris
 */

public class MoveIntakeArm extends Command {

    private double position;
    private final double SERVO_TRAVEL_TIME = 0.5;

    private ElapsedTime timer = new ElapsedTime();

    public MoveIntakeArm(double position) {
        requires(Subsystems.instance.glyphIntake);
        this.position = position;
    }

    @Override
    public void init() {
        Subsystems.instance.glyphIntake.setServoPosition(position);
        timer.reset();
    }

    @Override
    public void loop() {
        //this shouldn't be necessary but the servo is being a little shit right now
        Subsystems.instance.glyphIntake.setServoPosition(position);
    }

    @Override
    public boolean shouldRemove() {
        return timer.seconds() > SERVO_TRAVEL_TIME;
    }

    @Override
    protected void end() {

    }
}
