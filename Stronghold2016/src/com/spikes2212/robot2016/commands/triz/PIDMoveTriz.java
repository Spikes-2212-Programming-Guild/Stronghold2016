package com.spikes2212.robot2016.commands.triz;

import static com.spikes2212.robot2016.Robot.triz;

import com.spikes2212.robot2016.pid.PIDCommand;

public class PIDMoveTriz extends PIDCommand {

	private static /*final*/ double KD = 0;
	private static /*final*/ double KI = 0;
	private static /*final*/ double KP = 0;
	private static /*final*/ double ABSOLUTE_TOLERANCE = 0.01; // meter

	public PIDMoveTriz(double setpoint) {
		super(KP, KI, KD, setpoint, ABSOLUTE_TOLERANCE);
		requires(triz);
	}

	@Override
	protected void initialize() {
		triz.calibrate();
	}

	@Override
	protected void end() {
		triz.stop();
	}

	@Override
	public double getPIDInput() {
		return triz.getPosition();
	}

	@Override
	public void usePIDOutput(double output) {
		triz.tryMove(output);
	}

}
