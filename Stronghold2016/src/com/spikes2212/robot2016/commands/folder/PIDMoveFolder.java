package com.spikes2212.robot2016.commands.folder;

import static com.spikes2212.robot2016.Robot.folder;

import com.spikes2212.robot2016.pid.PIDCommand;

public class PIDMoveFolder extends PIDCommand {

	public static /* final */ double KD = 0;
	public static /* final */ double KI = 0;
	public static /* final */ double KP = 0;

	public PIDMoveFolder(double setpoint, double tolerance) {
		super(KP, KI, KD, setpoint, tolerance);
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
		return folder.getAngle();
	}

	@Override
	public void usePIDOutput(double output) {
		folder.tryMove(output);
	}

}
