package com.spikes2212.robot2016.commands.drivetrain;

import com.spikes2212.robot2016.Robot;
import com.spikes2212.robot2016.pid.PIDCalculator.AbsoluteTolerance;
import com.spikes2212.robot2016.pid.PIDCommand;

public class PIDStraightDriveByDistance extends PIDCommand {

	private static final double KP = 1;
	private static final double KI = 0;
	private static final double KD = 0;
	private static final double ABSOLUTE_TOLERANCE = 1; // centimeter

	private double maximumOutput;
	
	public PIDStraightDriveByDistance(double distance) {
		super(KP, KI, KD, distance, new AbsoluteTolerance(ABSOLUTE_TOLERANCE));
		requires(Robot.drivetrain);
	}

	@Override
	public double getPIDInput() {
		return 0.5 * (Robot.drivetrain.getLeftDistance() + Robot.drivetrain.getRightDistance());
	}

	@Override
	public void usePIDOutput(double output) {
		maximumOutput = Math.max(maximumOutput, Math.abs(output));
		Robot.drivetrain.forward(output / maximumOutput);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void end() {
		
	}
	
	

}
