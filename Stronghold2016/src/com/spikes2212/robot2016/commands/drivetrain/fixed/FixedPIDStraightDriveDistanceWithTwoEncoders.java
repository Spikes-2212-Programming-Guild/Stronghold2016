package com.spikes2212.robot2016.commands.drivetrain.fixed;

import static com.spikes2212.robot2016.Robot.drivetrain;

import com.spikes2212.robot2016.pid.DoublePIDCommand;

public class FixedPIDStraightDriveDistanceWithTwoEncoders extends DoublePIDCommand {

	public static /*final*/ double KP_LEFT = 1;
	public static /*final*/ double KI_LEFT = 0;
	public static /*final*/ double KD_LEFT = 0;
	public static /*final*/ double KP_RIGHT = 1;
	public static /*final*/ double KI_RIGHT = 0;
	public static /*final*/ double KD_RIGHT = 0;
	public static /*final*/ double TOLERANCE_LEFT = 1;
	public static /*final*/ double TOLERANCE_RIGHT = 0;

	public FixedPIDStraightDriveDistanceWithTwoEncoders(double distance) {
		super(KP_LEFT, KI_LEFT, KD_LEFT, distance, TOLERANCE_LEFT, KP_RIGHT, KI_RIGHT, KD_RIGHT, distance,
				TOLERANCE_RIGHT);
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
		drivetrain.setTwoSides(output1, output2);
	}

	@Override
	protected void initialize() {
		drivetrain.resetEncoders();
	}

	@Override
	protected void end() {
		drivetrain.stop();
	}

}
