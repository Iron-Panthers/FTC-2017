package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistanceInput;
import org.firstinspires.ftc.team7316.util.commands.drive.turn.TurnGyroInput;
import org.firstinspires.ftc.team7316.util.input.ButtonWrapper;
import org.firstinspires.ftc.team7316.util.input.GamepadButton;
import org.firstinspires.ftc.team7316.util.input.GamepadWrapper;
import org.firstinspires.ftc.team7316.util.input.OI;
import org.firstinspires.ftc.team7316.util.input.SingleButtonPressWrapper;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 1/23/18.
 */

@TeleOp(name = "button tune pid")
public class ButtonTunePID extends OpMode {

    private ElapsedTime timer = new ElapsedTime();
    private double previousTime = 0;
    private double timeThreshold = 1;

    private GamepadWrapper gp;

    @Override
    public void init() {
        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);
        Subsystems.createSubsystems();
        Scheduler.instance.clearBuffer();
        Scheduler.instance.clear();
        OI.createInputs(gamepad1, gamepad2);

        gp = OI.instance.gp1;

        timer.reset();
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
        Constants.halve = gp.right_bumper.state();

        if (gp.left_bumper.state()) {
            if (Constants.halve) {
                TurnGyroInput turn = new TurnGyroInput(Constants.GYRO_P,Constants.GYRO_I,Constants.GYRO_D,Constants.GYRO_F);
                Scheduler.instance.add(turn);
            } else {
                DriveDistanceInput drv = new DriveDistanceInput(Constants.DRIVE_P,Constants.DRIVE_I,Constants.DRIVE_D,Constants.DRIVE_F);
                Scheduler.instance.add(drv);
            }
        }

        Hardware.log("gryo p value", Constants.GYRO_P);
        Hardware.log("gryo i value", Constants.GYRO_I);
        Hardware.log("gryo d value", Constants.GYRO_D);
        Hardware.log("gryo f value", Constants.GYRO_F);
        Hardware.log("drive p value", Constants.DRIVE_P);
        Hardware.log("drive i value", Constants.DRIVE_I);
        Hardware.log("drive d value", Constants.DRIVE_D);
        Hardware.log("drive f value", Constants.DRIVE_F);
    }

}
