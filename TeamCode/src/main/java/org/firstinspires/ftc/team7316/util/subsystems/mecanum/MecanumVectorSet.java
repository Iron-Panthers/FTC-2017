package org.firstinspires.ftc.team7316.util.subsystems.mecanum;

import org.firstinspires.ftc.team7316.util.Vec2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Maxim on 9/13/2017.
 */

public class MecanumVectorSet {

    public double fl, fr, bl, br;

    public MecanumVectorSet(double fl, double fr, double bl, double br) {
        this.fl = fl;
        this.fr = fr;
        this.bl = bl;
        this.br = br;
    }

    public MecanumVectorSet(double left, double right) {
        this(right, left, right, left);
    }

    public static MecanumVectorSet moveInDirection(double theta) {
        double leftUp = Math.cos(theta - Math.PI / 2);
        double rightUp = Math.sin(theta - Math.PI / 2);
        return new MecanumVectorSet(rightUp, leftUp);
    }

    public MecanumVectorSet norm() {
        return scl(1 / mag());
    }

    public double mag() {
        return Collections.max(motorOutputs());
    }

    public Vec2 getPredictedTranslationVector() {
        return Vec2.X.scl(fl + br).add(Vec2.Y.scl(fr + bl));
    }

    public MecanumVectorSet add(MecanumVectorSet other) {
        return new MecanumVectorSet(this.fl + other.fl, this.fr + other.fr, this.bl + other.bl, this.br + other.br);
    }

    public MecanumVectorSet scl(double s) {
        return new MecanumVectorSet(fl * s, fr * s, bl * s, br * s);
    }

    private List<Double> motorOutputs() {
        List<Double> l = new ArrayList<>();
        l.add(fl);
        l.add(fr);
        l.add(bl);
        l.add(br);
        return l;
    }

}
