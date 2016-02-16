package com.spikes2212.robot2016.commands.drivetrain;

import static com.spikes2212.robot2016.Robot.drivetrain;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.pid.DoublePIDCommand;

public class JoystickPIDForwardDrive extends DoublePIDCommand {

	private SpeedSupplier forwardSpeed;

	private static final double LEFT_Y_TO_DISTANCE = 1;
	private static final double RIGHT_Y_TO_DISTANCE = 1;

	private static final double KP_LEFT = 1;
	private static final double KI_LEFT = 0;
	private static final double KD_LEFT = 9;
	private static final double KP_RIGHT = 1;
	private static final double KI_RIGHT = 0;
	private static final double KD_RIGHT = 9;
	private static final double TOLERANCE_LEFT = 0.01;
	private static final double TOLERANCE_RIGHT = 0.01;

	public JoystickPIDForwardDrive() {
		super(KP_LEFT, KI_LEFT, KD_LEFT, 0, TOLERANCE_LEFT, KP_RIGHT, KI_RIGHT, KD_RIGHT, 0, TOLERANCE_RIGHT);
		requires(drivetrain);
	}

	@Override
	public double getPIDInput1() {
		return drivetrain.getLeftDistance() - LEFT_Y_TO_DISTANCE * forwardSpeed.getSpeed();
	}

	@Override
	public double getPIDInput2() {
		return drivetrain.getRightDistance() - RIGHT_Y_TO_DISTANCE * forwardSpeed.getSpeed();
	}

	@Override
	public void usePIDOutput(double output1, double output2) {
		drivetrain.setTwoSides(output1 / Constants.MAX_LEFT_VELOCITY, output2 / Constants.MAX_RIGHT_VELOCITY);
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
