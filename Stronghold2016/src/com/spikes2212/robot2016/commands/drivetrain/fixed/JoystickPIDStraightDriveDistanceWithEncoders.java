package com.spikes2212.robot2016.commands.drivetrain.fixed;

import static com.spikes2212.robot2016.Robot.drivetrain;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.commands.drivetrain.SpeedSupplier;
import com.spikes2212.robot2016.pid.DoublePIDCommand;

public class JoystickPIDStraightDriveDistanceWithEncoders extends DoublePIDCommand {

	private static final double KP_LEFT = 1;
	private static final double KI_LEFT = 0;
	private static final double KD_LEFT = 0;
	private static final double KP_RIGHT = 1;
	private static final double KI_RIGHT = 0;
	private static final double KD_RIGHT = 0;
	private static final double TOLERANCE_LEFT = 1;
	private static final double TOLERANCE_RIGHT = 0;

	private SpeedSupplier leftSupplier;
	private SpeedSupplier rightSupplier;

	public JoystickPIDStraightDriveDistanceWithEncoders(SpeedSupplier leftSupplier, SpeedSupplier rightSupplier) {
		super(KP_LEFT, KI_LEFT, KD_LEFT, 0, TOLERANCE_LEFT, KP_RIGHT, KI_RIGHT, KD_RIGHT, 0, TOLERANCE_RIGHT);
		requires(drivetrain);
		this.leftSupplier = leftSupplier;
		this.rightSupplier = rightSupplier;
	}

	@Override
	public double getPIDInput1() {
		return leftSupplier.getSpeed() * Constants.MAX_LEFT_VELOCITY - drivetrain.getLeftVelocity();
	}

	@Override
	public double getPIDInput2() {
		return rightSupplier.getSpeed() * Constants.MAX_RIGHT_VELOCITY - drivetrain.getRightVelocity();
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
