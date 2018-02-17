package org.firstinspires.ftc.team7316.util.commands.drive.distance;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.copypastaLib.CombinedPath;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jerry on 10/28/17.
 */

public class DriveDistance extends Command {

    private int distance; // in ticks
    private int maxVelocity; // in ticks per second
    private final int MAXACCEL = 1500; //ticks per second per second

    private double timeout;
    private ElapsedTime timer;

    private int completedCount;
    private final int countThreshold = 10;

    private ArrayList<Double> times = new ArrayList<>();
    private ArrayList<Double> targets = new ArrayList<>();

    private ArrayList<Double> positionsFL = new ArrayList<>();
    private ArrayList<Double> positionsFR = new ArrayList<>();
    private ArrayList<Double> positionsBL = new ArrayList<>();
    private ArrayList<Double> positionsBR = new ArrayList<>();

    private ArrayList<Double> errorsFL = new ArrayList<>();
    private ArrayList<Double> errorsFR = new ArrayList<>();
    private ArrayList<Double> errorsBL = new ArrayList<>();
    private ArrayList<Double> errorsBR = new ArrayList<>();

    private ArrayList<Double> powerFL = new ArrayList<>();
    private ArrayList<Double> powerFR = new ArrayList<>();
    private ArrayList<Double> powerBL = new ArrayList<>();
    private ArrayList<Double> powerBR = new ArrayList<>();

    /**
     * @param ticks desired distance in ticks
     * @param maxVelocity maximum velocity the robot will run at
     * @param timeout the time before the command will force stop
     */
    public DriveDistance(int ticks, int maxVelocity, double timeout) {
        requires(Subsystems.instance.driveBase);
        this.distance = ticks;
        this.maxVelocity = maxVelocity;
        this.timeout = timeout;
        timer = new ElapsedTime();
    }

    public DriveDistance(int ticks, double timeout) {
        this(ticks, (int) (Constants.TICKS_PER_SECOND_HALFPOWER * 0.75), timeout);
    }

    public DriveDistance(int ticks) {
        this(ticks, 6);
    }

    @Override
    public void init() {
        timer.reset();
        completedCount = 0;
        Subsystems.instance.driveBase.resetMotors();
//        Subsystems.instance.driveBase.setMotorTargets(distance);
        if(distance > 0) {
            Subsystems.instance.driveBase.setMotorPaths(new CombinedPath.LongitudalTrapezoid(0, distance, maxVelocity, MAXACCEL));
        }
        else {
            Subsystems.instance.driveBase.setMotorPaths(new CombinedPath.LongitudalTrapezoid(0, distance, -maxVelocity, -MAXACCEL));
        }
    }

    @Override
    public void loop() {
        if(Subsystems.instance.driveBase.completedDistance()) {
            completedCount ++;
        }
        Subsystems.instance.driveBase.driveWithPID();
        Hardware.log("driving distance", "cool and good");
        Hardware.log("flError", Hardware.instance.frontLeftDriveMotorWrapper.getError());
        Hardware.log("frError", Hardware.instance.frontRightDriveMotorWrapper.getError());
        Hardware.log("current target", Hardware.instance.frontLeftDriveMotorWrapper.pid.getTargetTicksCurrent());

        times.add(Hardware.instance.backRightDriveMotorWrapper.pid.getElapsedSeconds());
        targets.add((double)Hardware.instance.backRightDriveMotorWrapper.pid.getTargetTicksCurrent());

        positionsFL.add((double)Hardware.instance.frontLeftDriveMotor.getCurrentPosition());
        positionsFR.add((double)Hardware.instance.frontRightDriveMotor.getCurrentPosition());
        positionsBL.add((double)Hardware.instance.backLeftDriveMotor.getCurrentPosition());
        positionsBR.add((double)Hardware.instance.backRightDriveMotor.getCurrentPosition());

        errorsFL.add((double) Hardware.instance.frontLeftDriveMotorWrapper.getError());
        errorsFR.add((double) Hardware.instance.frontRightDriveMotorWrapper.getError());
        errorsBL.add((double) Hardware.instance.backLeftDriveMotorWrapper.getError());
        errorsBR.add((double) Hardware.instance.backRightDriveMotorWrapper.getError());

        powerFL.add(Hardware.instance.frontLeftDriveMotorWrapper.pid.out * 100);
        powerFR.add(Hardware.instance.frontRightDriveMotorWrapper.pid.out * 100);
        powerBL.add(Hardware.instance.backLeftDriveMotorWrapper.pid.out * 100);
        powerBR.add(Hardware.instance.backRightDriveMotorWrapper.pid.out * 100);
    }

    @Override
    public boolean shouldRemove() {
        return completedCount >= countThreshold || timer.seconds() > timeout;
    }

    @Override
    protected void end() {
        Subsystems.instance.driveBase.stopMotors();
        writeCSV(times, targets, positionsFL, positionsFR, positionsBL, positionsBR, errorsFL, errorsFR, errorsBL, errorsBR, powerFL, powerFR, powerBL, powerBR);
    }

    /**
     * this kind of logging was a mistake
     */
    public static void writeCSV(List<Double> times, List<Double> targets,
                                List<Double> positionsFL, List<Double> positionsFR, List<Double> positionsBL, List<Double> positionsBR,
                                List<Double> errorsFL, List<Double> errorsFR, List<Double> errorsBL, List<Double> errorsBR,
                                List<Double> powerFL, List<Double> powerFR, List<Double> powerBL, List<Double> powerBR) {
        BufferedWriter os;
        Date date = new Date(System.currentTimeMillis());
        String timestamp = new SimpleDateFormat("yyyyMMdd-HH:mm:ss").format(date);
        try {
            File dir = new File("/storage/emulated/0/pidoutput-" + timestamp + ".csv");
            os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dir)));
            os.write("times,target," +
                    "positionsFL,positionsFR,positionsBL,positionsBR," +
                    "errorsFL,errorsFR,errorsBL,errorsBR," +
                    "powerFL,powerFR,powerBL,powerBR," +
                    "p,i,d,f,velocityPrediction,tolerance\n");
            os.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n", times.get(0), targets.get(0),
                    positionsFL.get(0), positionsFR.get(0), positionsBL.get(0), positionsBR.get(0),
                    errorsFL.get(0), errorsFR.get(0), errorsBL.get(0), errorsBR.get(0),
                    powerFL.get(0), powerFR.get(0), powerBL.get(0), powerBR.get(0),
                    Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D, Constants.DRIVE_F, Constants.COAST_TICKS_PER_SECOND, Constants.DISTANCE_ERROR_RANGE_TICKS));
            for (int i=1; i < times.size(); i++) {
                os.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n", times.get(i), targets.get(i),
                        positionsFL.get(i), positionsFR.get(i), positionsBL.get(i), positionsBR.get(i),
                        errorsFL.get(i), errorsFR.get(i), errorsBL.get(i), errorsBR.get(i),
                        powerFL.get(i), powerFR.get(i), powerBL.get(i), powerBR.get(i)));
            }
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
