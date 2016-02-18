package com.spikes2212.robot2016.commands.drivetrain;

import static com.spikes2212.robot2016.Robot.drivetrain;
import static edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.getNumber;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.pid.DoublePIDCommand;

public class PIDStraightDriveByDistance extends DoublePIDCommand {

	private static final double KP_LEFT = 1;
	private static final double KI_LEFT = 0;
	private static final double KD_LEFT = 9;
	private static final double KP_RIGHT = 1;
	private static final double KI_RIGHT = 0;
	private static final double KD_RIGHT = 9;
	private static final double TOLERANCE_LEFT = 0.01;
	private static final double TOLERANCE_RIGHT = 0.01;

	private double distance;

	public PIDStraightDriveByDistance(double distance) {
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
		// putNumber("PIDStraightDriveByDistance.DISTANCE", distance);
		this.distance = distance;
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
		getCalculator1().setPID(getNumber("PIDStraightDriveByDistance.KP_LEFT", KP_LEFT),
				getNumber("PIDStraightDriveByDistance.KI_LEFT", KI_LEFT),
				getNumber("PIDStraightDriveByDistance.KD_LEFT", KD_LEFT));
		getCalculator1().setTolerance(getNumber("PIDStraightDriveByDistance.TOLERANCE_LEFT", TOLERANCE_LEFT));
		getCalculator1().setSetpoint(getNumber("PIDStraightDriveByDistance.DISTANCE", distance));
		getCalculator2().setPID(getNumber("PIDStraightDriveByDistance.KP_RIGHT", KP_RIGHT),
				getNumber("PIDStraightDriveByDistance.KI_RIGHT", KI_RIGHT),
				getNumber("PIDStraightDriveByDistance.KD_RIGHT", KD_RIGHT));
		getCalculator2().setTolerance(getNumber("PIDStraightDriveByDistance.TOLERANCE_RIGHT", TOLERANCE_RIGHT));
		getCalculator2().setSetpoint(getNumber("PIDStraightDriveByDistance.DISTANCE", distance));
	}

	@Override
	protected void end() {
		drivetrain.stop();
	}

}
