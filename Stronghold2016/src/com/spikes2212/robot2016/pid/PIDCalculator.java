package com.spikes2212.robot2016.pid;

public class PIDCalculator {

	private double kp, ki, kd;
	private double pValue, iValue, dValue;
	private double setpoint;
	private double error, prevError;
	private double tolerance;
	private long t;

	public PIDCalculator() {
	}

	public double calculate(double input) {
		long curr = System.currentTimeMillis();
		long dt = curr - t;
		prevError = error;
		error = setpoint - input;
		pValue = kp * error;
		iValue += ki * (error * ((double)dt / 1000));
		dValue = kd * ((error - prevError) / (((double) dt) / 1000));
		t = curr;
		return getResult();
	}

	public double getResult() {
		return pValue + iValue + dValue;
	}

	public double getP() {
		return kp;
	}

	public double getI() {
		return ki;
	}

	public double getD() {
		return kd;
	}

	public void setPID(double p, double i, double d) {
		this.kp = p;
		this.ki = i;
		this.kd = d;
	}

	public void setSetpoint(double setpoint) {
		this.setpoint = setpoint;
		this.error = setpoint;
		this.t = System.currentTimeMillis();
	}

	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}

	public boolean hasReached() {
		return Math.abs(error) < tolerance;
	}

}
