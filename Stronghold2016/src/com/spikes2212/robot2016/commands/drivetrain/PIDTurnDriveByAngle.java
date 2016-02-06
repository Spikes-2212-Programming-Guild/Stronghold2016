package com.spikes2212.robot2016.commands.drivetrain;

import com.spikes2212.robot2016.Robot;
import com.spikes2212.robot2016.pid.PIDCalculator.AbsoluteTolerance;
import com.spikes2212.robot2016.pid.PIDCommand;

public class PIDTurnDriveByAngle extends PIDCommand {

	private static final double KP = 1;
	private static final double KI = 0;
	private static final double KD = 0;
	private static final double ABSOLUTE_TOLERANCE = 2; // degree

	public PIDTurnDriveByAngle(double angle) {
		super(KP, KI, KD, angle, new AbsoluteTolerance(ABSOLUTE_TOLERANCE));
		requires(Robot.drivetrain);
	}

	@Override
	public double getPIDInput() {
		return Robot.drivetrain.getYawAngle();
	}

	@Override
	public void usePIDOutput(double output) {
		Robot.drivetrain.turn(output);
	}

	@Override
	protected void initialize() {
		Robot.drivetrain.reset();
	}

	@Override
	protected void end() {

	}

}
