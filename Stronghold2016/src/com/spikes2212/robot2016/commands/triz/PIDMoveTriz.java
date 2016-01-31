package com.spikes2212.robot2016.commands.triz;

import static com.spikes2212.robot2016.Robot.triz;

import com.spikes2212.robot2016.pid.PIDCalculator.AbsoluteTolerance;
import com.spikes2212.robot2016.pid.PIDCommand;

public class PIDMoveTriz extends PIDCommand {
	private double maximumOutput;

	private static final double KD = 0;
	private static final double KI = 0;
	private static final double KP = 0;
	private static final double ABSOLUTE_TOLERANCE = 1; // centimeter

	private double firstPosition;

	public PIDMoveTriz(double setpoint) {
		super(KP, KI, KD, setpoint, new AbsoluteTolerance(ABSOLUTE_TOLERANCE));
		requires(triz);
	}

	@Override
	protected void initialize() {
		triz.calibrate();
		firstPosition = triz.getPosition();
	}

	@Override
	protected void end() {
		triz.stop();
	}

	@Override
	public double getPIDInput() {
		return triz.getPosition() - firstPosition;
	}

	@Override
	public void usePIDOutput(double output) {
		if (output != 0) {
			maximumOutput = Math.max(maximumOutput, Math.abs(output));
			output /= maximumOutput;
		}
		if (!(output > 0 && triz.isUp() || output < 0 && triz.isDown())) {
			triz.move(output);
		}
	}

}
