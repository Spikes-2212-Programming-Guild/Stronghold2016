package com.spikes2212.robot2016.util;

import java.util.ArrayList;

import javax.imageio.stream.FileImageInputStream;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.pid.PIDCalculator;
import com.sun.xml.internal.txw2.Document;

public class FeedForward {

	PIDCalculator pid;
	private double maxV, maxA, maxD, setpoint;
	/*
	 * the algorithem work by breaking the movement to 3 parts *
	 */
	double dt1, dt2, dt3;

	/**
	 * 
	 * @param p-
	 *            kp
	 * @param maxV
	 *            max velocity of subsystem
	 * @param maxA
	 *            max acceleration of subsystem
	 * @param maxD
	 *            max deceleration of subsystem
	 * @param setpoint
	 *            the distance to go
	 */
	public FeedForward(double p, double maxV, double maxA, double maxD, double setpoint) {
		this.setpoint = setpoint;
		pid = new PIDCalculator();
		pid.setPID(p, 0, 0);
		dt1 = (maxV / maxA);
		dt2 = (setpoint / maxV - maxV / (2 * maxA) - maxV / (2 * maxD));
		dt3 = maxV / maxD;
	}
/**
 * 
 * @param velocity
 * @param acceleration
 * @param location
 * @param expected
 * @return value for speed controller
 */
	//TODO: check return type
	public double getVoltage(double velocity, double acceleration, double location, double expected) {
		pid.setSetpoint(expected);
		double value = pid.calculate(location);
		return (Constants.VOLTAGE_VELOCITY_PARAMETER * (velocity + value)
				+ Constants.VOLTAGE_ACCELERATION_PARAMETER * acceleration);
	}

	public double[] getExpected(double t) {
		if (dt2 < 0) {
			return new double[] { 0, 0, 0 };
		}
		if (t < dt1) {
			return new double[] { maxA * t * t / 2, maxA * t, maxA };
		} else if (t >= dt1 && t < dt1 + dt2) {
			return new double[] { ((maxA * dt1 * dt1) / 2) + (t - dt1) * maxV, maxV, 0 };
		} else {
			double location = ((maxA * dt1 * dt1) / 2) + ((dt2 - dt1) * maxV) + ((t - dt1 - dt2) * (-maxD) / 2);
			return new double[] { location, 0, 0 };
		}
	}
}
