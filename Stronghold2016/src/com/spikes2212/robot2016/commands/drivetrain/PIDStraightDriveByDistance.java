package com.spikes2212.robot2016.commands.drivetrain;

import com.spikes2212.robot2016.Robot;
import com.spikes2212.robot2016.pid.PIDCommand;

public class PIDStraightDriveByDistance extends PIDCommand {

	public static /*final*/ double KP = 1;
	public static /*final*/ double KI = 0;
	public static /*final*/ double KD = 0;
	public static /*final*/ double ABSOLUTE_TOLERANCE = 1; // centimeter
	
	public static /*final*/ double DISTANCE = 0;

	public PIDStraightDriveByDistance() {
		super(KP, KI, KD, DISTANCE, ABSOLUTE_TOLERANCE);
		Robot.drivetrain.resetEncoders();
		requires(Robot.drivetrain);
	}

	@Override
	public double getPIDInput() {
		return (Robot.drivetrain.getLeftDistance() + Robot.drivetrain.getRightDistance()) / 2;
	}

	@Override
	public void usePIDOutput(double output) {
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
