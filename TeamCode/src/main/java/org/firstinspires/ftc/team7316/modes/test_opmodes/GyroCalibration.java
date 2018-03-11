package org.firstinspires.ftc.team7316.modes.test_opmodes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.sensors.GyroWrapper;

/**
 * Created by jerry on 3/3/18.
 */

@Autonomous
public class GyroCalibration extends AutoBaseOpMode {

    private BNO055IMU imu;
    private GyroWrapper gyro;

    @Override
    public void onInit() {
        imu = Hardware.instance.imu;
        gyro = Hardware.instance.gyroWrapper;
    }

    @Override
    public void onLoop() {
        Hardware.log("imu status", imu.getSystemStatus().toShortString());
        Hardware.log("calibration status", imu.getCalibrationStatus().toString());
        Hardware.log("gyro calibrated", imu.isGyroCalibrated());
        Hardware.log("gyro heading", gyro.getHeading());
        Hardware.log("gyro pitch", gyro.getPitch());
        Hardware.log("gyro roll", gyro.getRoll());
    }
}