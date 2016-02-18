package com.spikes2212.robot2016.commands.drivetrain;

import static edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.getNumber;
import static edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.putNumber;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.Robot;
import com.spikes2212.robot2016.pid.PIDCommand;

public class PIDTurnDriveByAngleush extends PIDCommand {

	private static final double KP = 1;
	private static final double KI = 0;
	private static final double KD = 0;
	private static final double TOLERANCE = 2; // degree

	private double initialAngle;
	private double angle;

	public PIDTurnDriveByAngleush(double angle) {
		this.angle = angle;
		Robot.drivetrain.resetGyro();
		putNumber("PIDTurnDriveByAngle.KP", KP);
		putNumber("PIDTurnDriveByAngle.KI", KI);
		putNumber("PIDTurnDriveByAngle.KD", KD);
		putNumber("PIDTurnDriveByAngle.TOLERANCE", TOLERANCE);
		putNumber("PIDTurnDriveByAngle.ANGLE", angle);
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
		getCalculator().setPID(getNumber("PIDTurnDriveByAngle.KP", KP), getNumber("PIDTurnDriveByAngle.KI", KI),
				getNumber("PIDTurnDriveByAngle.KD", KD));
		getCalculator().setTolerance(getNumber("PIDTurnDriveByAngle.TOLERANCE", TOLERANCE));
		getCalculator().setSetpoint(getNumber("PIDTurnDriveByAngle.ANGLE", angle - initialAngle));
	}

	@Override
	protected void end() {

	}

}
