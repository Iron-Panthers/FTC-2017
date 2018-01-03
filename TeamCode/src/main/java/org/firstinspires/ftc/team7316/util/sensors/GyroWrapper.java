package org.firstinspires.ftc.team7316.util.sensors;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.team7316.util.Hardware;

/**
 * Created by jerry on 11/10/17.
 */

public class GyroWrapper {

    private BNO055IMU gyro;

    public GyroWrapper(BNO055IMU gyro) {
        this.gyro = gyro;
    }

    /**
     * turning right is apparently negative so just multiply it by -1 lul
     * @return
     */
    public double getHeading() {
//        return gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        return gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle * -1;
    }

    public double getPitch() {
        return gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).secondAngle;
    }

    public double getRoll() {
        return gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).thirdAngle;
    }

    public void logAngles() {
        Orientation o = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
        Hardware.log("gyro x-axis", o.firstAngle);
        Hardware.log("gyro y-axis", o.secondAngle);
        Hardware.log("gyro z-axis", o.thirdAngle);
    }
}
