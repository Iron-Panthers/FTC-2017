package org.firstinspires.ftc.team7316.util;

/**
 * Created by Maxim on 2/17/2017.
 */

public class Util {

    /**
     * Map a value x in the range [a1, b1] to a new value in the range [a2, b2]
     * @param x
     * @param a1
     * @param b1
     * @param a2
     * @param b2
     * @return
     */
    public static double map(double x, double a1, double b1, double a2, double b2) {
        return (b2 - a2) * (x - a1) / (b1 - a1) + a2;
    }

    public static double modBueno(double a, double b) {
        if (a > 0) {
            return a % b;
        } else {
            return a % b + b;
        }
    }

    public static float wrap(float theta) {
        float num = (theta - 180) % (360);

        if (num < 0) {
            num += 180;
        } else {
            num -= 180;
        }
        return num;
    }
}
