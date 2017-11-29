package org.firstinspires.ftc.team7316.util;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    public static double wrap(double theta) {
        double num = (theta - 180) % (360);

        if (num < 0) {
            num += 180;
        } else {
            num -= 180;
        }
        return num;
    }

    public static void writeCSV(List<Double> times, List<Double> targets, List<Double> positions) {
        BufferedWriter os;
        Date date = new Date(System.currentTimeMillis());
        String timestamp = new SimpleDateFormat("yyyyMMdd-HH:mm:ss").format(date);
        try {
            File dir = new File(Hardware.instance.appContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), timestamp + ".csv");
            os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dir)));
            os.write("times,target,positions\n");
            for (int i=0; i < times.size(); i++) {
                os.write(String.format("%s,%s,%s\n", times.get(i), targets.get(i), positions.get(i)));
            }
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
