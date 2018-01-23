package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team7316.modes.TeleopBaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistanceInput;
import org.firstinspires.ftc.team7316.util.input.GamepadButton;
import org.firstinspires.ftc.team7316.util.input.GamepadWrapper;
import org.firstinspires.ftc.team7316.util.input.OI;
import org.firstinspires.ftc.team7316.util.input.SingleButtonPressWrapper;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

/**
 * Created by jerry on 1/23/18.
 */

public class ButtonTunePID extends OpMode {

    private double p = 0;
    private double i = 0;
    private double d = 0;
    private double f = 0;

    private GamepadWrapper gp;

    @Override
    public void init() {
        Subsystems.createSubsystems();
        Scheduler.instance.clear();
        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);
        OI.createInputs(gamepad1, gamepad2);

        gp = OI.instance.gp1;

        gp.modifyButton(gp.a_button, new SingleButtonPressWrapper(GamepadButton.A_BUTTON, gp));
        gp.modifyButton(gp.b_button, new SingleButtonPressWrapper(GamepadButton.B_BUTTON, gp));
        gp.modifyButton(gp.x_button, new SingleButtonPressWrapper(GamepadButton.X_BUTTON, gp));
        gp.modifyButton(gp.y_button, new SingleButtonPressWrapper(GamepadButton.Y_BUTTON, gp));
    }

    @Override
    public void loop() {
        boolean triggerpressed = gp.right_bumper.state();
        if(gp.left_bumper.state()) {
            DriveDistanceInput drv = new DriveDistanceInput(p,i,d,f);
            drv.init();
            while (!drv.shouldRemove()) {
                drv.loop();
            }
            drv.interrupt();
        }
        else if(gp.a_button.state()) {
            p = (triggerpressed) ? p / 2: p * 2;
        }
        else if(gp.b_button.state()) {
            i = (triggerpressed) ? i / 2: i * 2;
        }
        else if(gp.x_button.state()) {
            d = (triggerpressed) ? d / 2: d * 2;
        }
        else if(gp.y_button.state()) {
            f = (triggerpressed) ? f / 2: f * 2;
        }
        Hardware.log("p value", p);
        Hardware.log("i value", i);
        Hardware.log("d value", d);
        Hardware.log("f value", f);
    }
}
