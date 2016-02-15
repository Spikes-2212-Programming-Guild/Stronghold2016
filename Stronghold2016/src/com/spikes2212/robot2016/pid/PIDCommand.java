package com.spikes2212.robot2016.pid;

import edu.wpi.first.wpilibj.command.Command;

public abstract class PIDCommand extends Command {

	private PIDCalculator calculator;

	public PIDCommand() {
		calculator = new PIDCalculator();
	}

	public PIDCommand(double kp, double ki, double kd, double setpoint, double tolerance) {
		this();
		calculator.setPID(kp, ki, kd);
		calculator.setSetpoint(setpoint);
		calculator.setTolerance(tolerance);
	}

	public abstract double getPIDInput();

	protected PIDCalculator getCalculator() {
		return calculator;
	}

	public abstract void usePIDOutput(double output);

	@Override
	protected void execute() {
		usePIDOutput(calculator.calculate(getPIDInput()));
	}

	@Override
	protected boolean isFinished() {
		return calculator.hasReached();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
