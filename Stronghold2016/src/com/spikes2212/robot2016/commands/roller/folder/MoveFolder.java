package com.spikes2212.robot2016.commands.roller.folder;

import com.spikes2212.robot2016.Robot;
import com.spikes2212.robot2016.pid.PIDCommand;

public class MoveFolder extends PIDCommand {
	private double maximumOutput;

	private static final double KD = 0;
	private static final double KI = 0;
	private static final double KP = 0;

	public MoveFolder(double setpoint) {
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
		if (output != 0) {
			maximumOutput = Math.max(maximumOutput, Math.abs(output));
			output /= maximumOutput;
		}
		if (!(output > 0 && Robot.triz.isUp() || output < 0 && Robot.triz.isDown())) {
			Robot.triz.moveTriz(output);
		}
	}

}
