package com.spikes2212.robot2016.commands.folder;

import static com.spikes2212.robot2016.Robot.folder;

import com.spikes2212.robot2016.pid.PIDCalculator.Tolerance;
import com.spikes2212.robot2016.pid.PIDCommand;

public class PIDMoveFolder extends PIDCommand {
	private double maximumOutput;

	private static final double KD = 0;
	private static final double KI = 0;
	private static final double KP = 0;

	public PIDMoveFolder(double setpoint, Tolerance tolerance) {
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
		return folder.getPosition();
	}

	@Override
	public void usePIDOutput(double output) {
		if (output != 0) {
			maximumOutput = Math.max(maximumOutput, Math.abs(output));
			output /= maximumOutput;
		}
		if (!(output > 0 && folder.isUp() || output < 0 && folder.isDown())) {
			folder.move(output);
		}
	}

}
