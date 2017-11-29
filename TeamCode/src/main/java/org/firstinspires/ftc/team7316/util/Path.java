package org.firstinspires.ftc.team7316.util;

public class Path {
	
	private static Type type;
	private static double[] parameters;

	public double speed(double time) {
		switch (type) {
			case LINEAR:
				if (time < parameters[0]) {
					return parameters[1];
				} else {
					return 0;
				}

			case TRAPEZOIDAL:

				double accel = parameters[3];
				double maxSpeed = parameters[4];

				if (time < parameters[0]) {
					return time * accel;
				} else if (time < parameters[1]) {
					return maxSpeed;
				} else if (time < parameters[2]) {
					return -(time - parameters[1]) * accel + maxSpeed;
				} else {
					return 0;
				}

			case CURVE:
				break;
		}
		return 0;
	}

	private enum Type {
		LINEAR, TRAPEZOIDAL, CURVE;
	}

	public static Path linear(double maxSpeed, double distance) {
		type = Type.LINEAR;

		double time = distance/maxSpeed;

		parameters = new double[]{time, maxSpeed};
		return null;
	}

	public static Path trapezoid(double acceleration, double maxSpeed, double distance) {
		type = Type.TRAPEZOIDAL;

		double accelTime = maxSpeed/acceleration;

		double remainingDistance = distance - maxSpeed * accelTime;
		double flatTimeEnd = remainingDistance / maxSpeed + accelTime;

		parameters = new double[]{accelTime, flatTimeEnd, flatTimeEnd + accelTime, acceleration, maxSpeed};
		return null;
	}

	public static Path curve(double speed, double endTime) {
		type = Type.CURVE;
		parameters = new double[]{speed, endTime};
		return null;
	}

}
