package org.firstinspires.ftc.team7316.util.input;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.subsystems.MecanumDriveBase;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by andrew on 10/18/17.
 */

public class OI {

    public static OI instance = null;

    public GamepadWrapper gp1;

    public OI(Gamepad gamepad1, Gamepad gamepad2) {

        gp1 = new GamepadWrapper(gamepad1);

    }

    public static void createInputs(Gamepad gamepad1, Gamepad gamepad2) {
        instance = new OI(gamepad1, gamepad2);
    }

}
