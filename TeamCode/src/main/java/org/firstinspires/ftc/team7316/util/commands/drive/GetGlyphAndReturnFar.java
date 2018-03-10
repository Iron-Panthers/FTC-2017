package org.firstinspires.ftc.team7316.util.commands.drive;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistance;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroPID;
import org.firstinspires.ftc.team7316.util.commands.intake.RunIntake;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 3/9/18.
 */

public class GetGlyphAndReturnFar extends Command {

    private int encoderTicksTravelled = 0;
    private double driveAngle;
    private Command currentCommand;
    private RunIntake runIntake;
    private int commandIndex = 0;

    public GetGlyphAndReturnFar(double driveAngle) {
        requires(Subsystems.instance.driveBase);
        requires(Subsystems.instance.glyphIntake);

        this.driveAngle = driveAngle;
    }

    @Override
    public void init() {
        encoderTicksTravelled = 0;
        commandIndex = 0;
        currentCommand = new TurnGyroPID(driveAngle, 3);
        currentCommand.init();
    }

    @Override
    public void loop() {
        currentCommand.loop();

        if (commandIndex == 0) {
            if (currentCommand.shouldRemove()) {
                currentCommand.interrupt();

                currentCommand = new DriveDistance(Constants.MAX_TICKS_TO_MULTIGLYPH_FAR);
                runIntake = new RunIntake(-0.7);
                Subsystems.instance.glyphIntake.setServoPosition(Constants.INTAKE_SERVO_MAX_POSITION);

                currentCommand.init();
                runIntake.init();
                commandIndex++;
            }
        } else if (commandIndex == 1) {
            runIntake.loop();

            if (currentCommand.shouldRemove() || Hardware.instance.glyphTouchSensor.isPressed()) {
                encoderTicksTravelled = Hardware.instance.frontLeftDriveMotor.getCurrentPosition();

                currentCommand.interrupt();
                Subsystems.instance.glyphIntake.setServoPosition(Constants.INTAKE_CLAMP_GLYPH_POSITION);

                currentCommand = new TurnGyroPID(driveAngle + 175, 2.5);
                currentCommand.init();
                commandIndex++;
            }
        } else if (commandIndex == 2) {
            runIntake.loop();

            if (currentCommand.shouldRemove()) {
                currentCommand.interrupt();
                runIntake.interrupt();

                currentCommand = new DriveDistance(encoderTicksTravelled);
                currentCommand.init();
                commandIndex++;
            }
        } else if (commandIndex == 3) {
            if (currentCommand.shouldRemove()) {
                currentCommand.interrupt();
                commandIndex++;
            }
        }
    }

    @Override
    public boolean shouldRemove() {
        return commandIndex >= 4;
    }

    @Override
    protected void end() {
        Subsystems.instance.driveBase.stopMotors();
    }
}
