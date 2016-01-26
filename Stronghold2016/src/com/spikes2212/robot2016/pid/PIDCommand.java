package com.spikes2212.robot2016.pid;

import edu.wpi.first.wpilibj.command.Command;

public abstract class PIDCommand extends Command {

	private PIDCalculator calculator;

	public PIDCommand(double kp, double ki, double kd, double setpoint) {
		calculator = new PIDCalculator(kp, ki, kd);
		calculator.setSetpoint(setpoint);
	}

	public abstract double getPIDInput();

	public abstract void usePIDOutput(double output);

	@Override
	protected void execute() {
		usePIDOutput(getPIDInput());
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