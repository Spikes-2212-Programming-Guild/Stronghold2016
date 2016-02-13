package com.spikes2212.robot2016.commands.drivetrain;

import com.spikes2212.robot2016.Robot;
import com.spikes2212.robot2016.pid.PIDCommand;

public class PIDTurnDriveByAngle extends PIDCommand {

	public static /*final*/ double KP = 1;
	public static /*final*/ double KI = 0;
	public static /*final*/ double KD = 0;
	public static /*final*/ double ABSOLUTE_TOLERANCE = 2; // degree
	
	public static /*final*/ double ANGLE = 0;
	
	private double initialAngle;
	
	public PIDTurnDriveByAngle() {
		super(KP, KI, KD, ANGLE, ABSOLUTE_TOLERANCE);
		requires(Robot.drivetrain);
	}

	@Override
	public double getPIDInput() {
		return Robot.drivetrain.getYawAngle() - initialAngle;
	}

	@Override
	public void usePIDOutput(double output) {
		Robot.drivetrain.turn(output);
	}

	@Override
	protected void initialize() {
		initialAngle = Robot.drivetrain.getYawAngle();
	}

	@Override
	protected void end() {

	}

}
