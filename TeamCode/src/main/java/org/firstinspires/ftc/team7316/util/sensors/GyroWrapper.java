package org.firstinspires.ftc.team7316.util.sensors;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by jerry on 11/10/17.
 */

public class GyroWrapper {

    private BNO055IMU gyro;

    public GyroWrapper(BNO055IMU gyro) {
        this.gyro = gyro;
    }

    public double getHeading() {
        return gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    }
}
