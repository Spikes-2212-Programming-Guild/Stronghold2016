package com.spikes2212.robot2016.commands.drivetrain.fixed;

import static com.spikes2212.robot2016.Robot.drivetrain;

import com.spikes2212.robot2016.pid.DoublePIDCommand;

public class FixedPIDStraightDriveDistanceWithEncoderDifference extends DoublePIDCommand {

	public static /*final*/ double KP_STRAIGHT = 1;
	public static /*final*/ double KI_STRAIGHT = 0;
	public static /*final*/ double KD_STRAIGHT = 0;
	public static /*final*/ double KP_TURN = 1;
	public static /*final*/ double KI_TURN = 0;
	public static /*final*/ double KD_TURN = 0;
	public static /*final*/ double TOLERANCE_STRAIGHT = 1;
	public static /*final*/ double TOLERANCE_TURN = 0;

	public FixedPIDStraightDriveDistanceWithEncoderDifference(double distance) {
		super(KP_STRAIGHT, KI_STRAIGHT, KD_STRAIGHT, distance, TOLERANCE_STRAIGHT, KP_TURN, KI_TURN, KD_TURN, 0,
				TOLERANCE_TURN);
		requires(drivetrain);
	}

	@Override
	public double getPIDInput1() {
		return 0.5 * (drivetrain.getLeftDistance() + drivetrain.getRightDistance());
	}

	@Override
	public double getPIDInput2() {
		return drivetrain.getLeftDistance() - drivetrain.getRightDistance();
	}

	@Override
	public void usePIDOutput(double output1, double output2) {
		drivetrain.arcade(output1, output2);
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
