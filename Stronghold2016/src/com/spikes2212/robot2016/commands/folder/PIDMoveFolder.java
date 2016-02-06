package com.spikes2212.robot2016.commands.folder;

import static com.spikes2212.robot2016.Robot.folder;

import com.spikes2212.robot2016.pid.PIDCommand;

public class PIDMoveFolder extends PIDCommand {

	private static final double KD = 0;
	private static final double KI = 0;
	private static final double KP = 0;

	private static final double TOLERANCE = 0;

	public PIDMoveFolder(double setpoint) {
		super(KP, KI, KD, setpoint, TOLERANCE);
		requires(folder);
	}

	@Override
	protected void initialize() {
		folder.calibrate();
	}

	@Override
	protected void end() {
		folder.stop();
	}

	@Override
	public double getPIDInput() {
		return folder.getPosition();
	}

	@Override
	public void usePIDOutput(double output) {
		folder.tryMove(output);
	}

}
