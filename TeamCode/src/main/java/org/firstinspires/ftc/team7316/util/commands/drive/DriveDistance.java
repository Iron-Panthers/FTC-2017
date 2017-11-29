package org.firstinspires.ftc.team7316.util.commands.drive;

import android.content.Context;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Util;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jerry on 10/28/17.
 */

public class DriveDistance extends Command {

    private int distance; // in ticks

    private double timeout;
    private ElapsedTime timer;

    private int completedCount;
    private final int countThreshold = 10;

    private ArrayList<Double> times = new ArrayList<>();
    private ArrayList<Double> targets = new ArrayList<>();
    private ArrayList<Double> positions = new ArrayList<>();

    public DriveDistance(double inches) {
        //requires(Subsystems.instance.driveBase);
        this.distance = (int)Constants.inchesToTicks(inches);
        this.timeout = 100;
        timer = new ElapsedTime();
    }

    /**
     * @param inches desired distance
     * @param timeout the time before the command will stop
     */
    public DriveDistance(double inches, double timeout) {
        //requires(Subsystems.instance.driveBase);
        this.distance = (int)Constants.inchesToTicks(inches);
        this.timeout = timeout;
        timer = new ElapsedTime();
    }

    @Override
    public void init() {
        timer.reset();
        completedCount = 0;
        Subsystems.instance.driveBase.resetMotors();
        Subsystems.instance.driveBase.setMotorTargets(distance);
    }

    @Override
    public void loop() {
        if(Subsystems.instance.driveBase.completedDistance()) {
            completedCount ++;
        }
        Hardware.log("driving distance", "cool and good");
        Hardware.log("flError", Hardware.instance.frontLeftDriveMotorWrapper.getError());
        Hardware.log("frError", Hardware.instance.frontRightDriveMotorWrapper.getError());
        Hardware.log("current target", Hardware.instance.frontLeftDriveMotorWrapper.pid.getTargetTicksCurrent());
        times.add(Hardware.instance.frontLeftDriveMotorWrapper.pid.getElapsedSeconds());
        targets.add((double)Hardware.instance.frontLeftDriveMotorWrapper.pid.getTargetTicksCurrent());
        positions.add((double)Hardware.instance.frontLeftDriveMotor.getCurrentPosition());
        Subsystems.instance.driveBase.driveWithSpeedsPID();
    }

    @Override
    public boolean shouldRemove() {
        return completedCount >= countThreshold || timer.seconds() > timeout;
    }

    @Override
    protected void end() {
        Subsystems.instance.driveBase.stopMotors();
        Util.writeCSV(times, targets, positions);
    }

}
