package com.spikes2212.robot2016.commands.triz;

import com.spikes2212.robot2016.Robot;
import com.spikes2212.robot2016.pid.PIDCommand;

public class LiftPortcullis extends PIDCommand {
	private double maximumOutput;

	private static final double KD = 0;
	private static final double KI = 0;
	private static final double KP = 0;

	public LiftPortcullis(double setpoint) {
		super(KP, KI, KD, setpoint);
		requires(Robot.triz);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void end() {
		Robot.triz.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	public double getPIDInput() {
		return Robot.triz.getDistance();
	}

	@Override
	public void usePIDOutput(double output) {
		maximumOutput = Math.max(maximumOutput, Math.abs(output));
		Robot.triz.moveTriz(output / maximumOutput);
	}

}
