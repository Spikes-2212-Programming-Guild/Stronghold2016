package com.spikes2212.robot2016.pid;

public class PIDCalculator {

	public static final long DEFAULT_DT = 30;

	public interface Tolerance {
		boolean hasReached(double setpoint, double error);
	}

	public static class AbsoluteTolerance implements Tolerance {
		private double value;

		public AbsoluteTolerance(double value) {
			this.value = value;
		}

		@Override
		public boolean hasReached(double setpoint, double error) {
			return Math.abs(error) < value;
		}
	}

	public static class RelativeTolerance implements Tolerance {
		private double value;

		public RelativeTolerance(double value) {
			this.value = value;
		}

		@Override
		public boolean hasReached(double setpoint, double error) {
			return Math.abs(error / setpoint) < value;
		}
	}

	private double kp, ki, kd;
	private double pValue, iValue, dValue;
	private double setpoint;
	private double error, prevError;
	private Tolerance tolerance;

	public PIDCalculator(double kp, double ki, double kd) {
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
	}

	public double calculate(double input) {
		prevError = error;
		error = setpoint - input;
		pValue = kp * error;
		iValue += ki * error;
		dValue = kd * (error - prevError);
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

	public void setTolerance(Tolerance tolerance) {
		this.tolerance = tolerance;
	}

	public boolean hasReached() {
		return tolerance.hasReached(setpoint, error);
	}

}
