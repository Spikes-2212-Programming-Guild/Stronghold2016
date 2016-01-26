package com.spikes2212.robot2016.pid;

public class PIDCalculator {

	public static final long DEFAULT_DT = 30;
	private long lastTime = 0;
	private double kp, ki, kd;
	private double pValue, iValue, dValue;
	private double setpoint;
	private double error, prevError;
	private double tolerance;

	public PIDCalculator(double kp, double ki, double kd) {
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
	}

	public double calculate(double input) {
		long time = System.currentTimeMillis();
		if (lastTime != 0) {
			long dt = time - lastTime;
			prevError = error;
			error = setpoint - input;
			pValue = kp * error;
			iValue += ki * (prevError + error) * dt / 2;
			dValue = kd * (error - prevError) / dt;
		}
		lastTime = time;
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
	}

	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}

	public boolean hasReached() {
		return Math.abs(error) < tolerance;
	}

}
