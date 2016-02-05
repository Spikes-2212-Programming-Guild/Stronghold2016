package com.spikes2212.robot2016.commands.drivetrain;

import com.spikes2212.robot2016.Robot;
import com.spikes2212.robot2016.pid.PIDCommand;

public class PIDStraightDriveByDistance extends PIDCommand {

	private static final double KP = 1;
	private static final double KI = 0;
	private static final double KD = 0;
	private static final double ABSOLUTE_TOLERANCE = 1; // centimeter

	private double maximumOutput;

	public PIDStraightDriveByDistance(double distance) {
		super(KP, KI, KD, distance, ABSOLUTE_TOLERANCE);
		Robot.drivetrain.resetEncoders();
		requires(Robot.drivetrain);
	}

	@Override
	public double getPIDInput() {
		return (Robot.drivetrain.getLeftDistance() + Robot.drivetrain.getRightDistance()) / 2;
	}

	@Override
	public void usePIDOutput(double output) {
		
		if (output != 0) {
			maximumOutput = Math.max(maximumOutput, Math.abs(output));
			output /= maximumOutput;
		}
		Robot.drivetrain.forward(output);
	}

	@Override
	protected void initialize() {
		Robot.drivetrain.resetEncoders();
	}

	@Override
	protected void end() {

	}

}
