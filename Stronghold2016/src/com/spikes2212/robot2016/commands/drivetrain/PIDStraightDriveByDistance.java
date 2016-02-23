package com.spikes2212.robot2016.commands.drivetrain;

import static com.spikes2212.robot2016.Robot.drivetrain;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.pid.DoublePIDCommand;

public class PIDStraightDriveByDistance extends DoublePIDCommand {

	private static final double KP = 2;
	private static final double KI = 0.0;
	private static final double KD = 8;
	private static final double TOLERANCE = 0.01;

	private double distance;
	private double maxSpeed;

	public PIDStraightDriveByDistance(double distance, double maxSpeed) {
		// putNumber("PIDStraightDriveByDistance.KP_LEFT", KP_LEFT);
		// putNumber("PIDStraightDriveByDistance.KI_LEFT", KI_LEFT);
		// putNumber("PIDStraightDriveByDistance.KD_LEFT", KD_LEFT);
		// putNumber("PIDStraightDriveByDistance.TOLERANCE_LEFT",
		// TOLERANCE_LEFT);
		// putNumber("PIDStraightDriveByDistance.KP_RIGHT", KP_RIGHT);
		// putNumber("PIDStraightDriveByDistance.KI_RIGHT", KI_RIGHT);
		// putNumber("PIDStraightDriveByDistance.KD_RIGHT", KD_RIGHT);
		// putNumber("PIDStraightDriveByDistance.TOLERANCE_RIGHT",
		// TOLERANCE_RIGHT);
		// SmartDashboard.putNumber("PIDStraightDriveByDistance.DISTANCE",
		// distance);
		this.distance = distance;
		this.maxSpeed = maxSpeed;
		requires(drivetrain);
	}

	@Override
	public double getPIDInput1() {
		return drivetrain.getLeftDistance();
	}

	@Override
	public double getPIDInput2() {
		return drivetrain.getRightDistance();
	}

	@Override
	public void usePIDOutput(double output1, double output2) {
		drivetrain.setTwoSides(output1 / Constants.MAX_LEFT_VELOCITY, output2 / Constants.MAX_RIGHT_VELOCITY);
	}

	@Override
	protected void initialize() {
		drivetrain.resetEncoders();
		drivetrain.setMaximumSpeed(maxSpeed);
		getCalculator1().setPID(KP, KI, KD);
		getCalculator1().setTolerance(TOLERANCE);
		getCalculator1().setSetpoint(distance);
		getCalculator2().setPID(KP, KI, KD);
		getCalculator2().setTolerance(TOLERANCE);
		getCalculator2().setSetpoint(distance);
	}

	@Override
	protected void end() {
		drivetrain.stop();
	}

}
