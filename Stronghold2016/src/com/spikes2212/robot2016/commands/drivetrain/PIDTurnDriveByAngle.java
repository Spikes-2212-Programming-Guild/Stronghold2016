package com.spikes2212.robot2016.commands.drivetrain;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.Robot;
import com.spikes2212.robot2016.pid.PIDCommand;

public class PIDTurnDriveByAngle extends PIDCommand {

	private static final double KP = 0.06;
	private static final double KI = 0;
	private static final double KD = 0.08;
	private static final double TOLERANCE = 1; // degree

	private double initialAngle;
	private double angle;

	public PIDTurnDriveByAngle(double angle) {
		this.angle = angle;
		Robot.drivetrain.setMaximumSpeed(Constants.HIGH_MAX_SPEED);
		requires(Robot.drivetrain);
	}

	@Override
	public double getPIDInput() {
		return Robot.drivetrain.getYawAngle() - initialAngle;
	}

	@Override
	public void usePIDOutput(double output) {
		Robot.drivetrain.turn(output / Constants.MAX_LEFT_VELOCITY);
	}

	@Override
	protected void initialize() {
		initialAngle = Robot.drivetrain.getYawAngle();
		getCalculator().setPID(KP, KI,KD);
		getCalculator().setTolerance(TOLERANCE);
		getCalculator().setSetpoint(angle);
	}

	@Override
	protected void end() {

	}

}
