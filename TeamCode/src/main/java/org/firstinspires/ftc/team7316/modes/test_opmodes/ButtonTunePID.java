package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.distance.DriveDistanceInput;
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

    private double p = 0.015;
    private double i = 0.0002;
    private double d = 0.00008;
    private double f = 0.00007;

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
            if(deltaTime() > timeThreshold) {
                p = (triggerpressed) ? p / 2: p * 2;
            }
            else {
                previousTime = timer.seconds();
            }
        }
        else if(gp.b_button.state()) {
            if(deltaTime() > timeThreshold) {
                i = (triggerpressed) ? i / 2 : i * 2;
            }
            else {
                previousTime = timer.seconds();
            }
        }
        else if(gp.x_button.state()) {
            if(deltaTime() > timeThreshold) {
                d = (triggerpressed) ? d / 2 : d * 2;
            }
            else {
                previousTime = timer.seconds();
            }
        }
        else if(gp.y_button.state()) {
            if(deltaTime() > timeThreshold) {
                f = (triggerpressed) ? f / 2 : f * 2;
            }
            else {
                previousTime = timer.seconds();
            }
        }
        Hardware.log("p value", p);
        Hardware.log("i value", i);
        Hardware.log("d value", d);
        Hardware.log("f value", f);
    }

    private double deltaTime() {
        return timer.seconds() - previousTime;
    }
}
