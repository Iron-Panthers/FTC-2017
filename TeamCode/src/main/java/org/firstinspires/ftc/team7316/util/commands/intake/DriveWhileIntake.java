package org.firstinspires.ftc.team7316.util.commands.intake;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 1/29/18.
 */

public class DriveWhileIntake extends Command {

    private ElapsedTime timer = new ElapsedTime();

    private double intakePower;
    private double drivePower;
    private double time;

    public DriveWhileIntake(double intakePower, double drivePower, double time) {
        requires(Subsystems.instance.driveBase);
        requires(Subsystems.instance.glyphIntake);

        this.intakePower = intakePower;
        this.drivePower = drivePower;
        this.time = time;
    }

    @Override
    public void init() {
        Subsystems.instance.glyphIntake.setServoPositionScaled(0.1);
        timer.reset();
    }

    @Override
    public void loop() {
        Subsystems.instance.driveBase.setMotorPowers(drivePower);
        Subsystems.instance.glyphIntake.setIntakePower(intakePower);
    }

    @Override
    public boolean shouldRemove() {
        return timer.seconds() >= time;
    }

    @Override
    protected void end() {
        Subsystems.instance.glyphIntake.setServoPosition(Constants.INTAKE_CLAMP_GLYPH_POSITION);
        Subsystems.instance.driveBase.stopMotors();
        Subsystems.instance.glyphIntake.setIntakePower(0);
    }
}
