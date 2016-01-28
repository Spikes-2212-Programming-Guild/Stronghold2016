package com.spikes2212.robot2016.commands.drivetrain;

import com.spikes2212.robot2016.Robot;
import com.spikes2212.robot2016.pid.PIDCalculator.AbsoluteTolerance;
import com.spikes2212.robot2016.pid.PIDCommand;

public class TurnDriveByAngle extends PIDCommand {

	private double maximumOutput;

	private static final double KP = 1;
	private static final double KI = 0;
	private static final double KD = 0;
	private static final double ABSOLUTE_TOLERANCE = 2; // degree

	public TurnDriveByAngle(double angle) {
		super(KP, KI, KD, angle, new AbsoluteTolerance(ABSOLUTE_TOLERANCE));
		requires(Robot.drivetrain);
	}

	@Override
	public double getPIDInput() {
		return Robot.drivetrain.getYawAngle();
	}

	@Override
	public void usePIDOutput(double output) {
		maximumOutput = Math.max(maximumOutput, Math.abs(output));
		Robot.drivetrain.turn(output / maximumOutput);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void end() {
		
	}
	
	

}
