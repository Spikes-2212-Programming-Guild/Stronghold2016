package com.spikes2212.robot2016.commands.drivetrain;

import static com.spikes2212.robot2016.Robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TwoJoysticksDrive extends Command {

	private SpeedSupplier leftSpeed;
	private SpeedSupplier rightSpeed;

	public TwoJoysticksDrive(SpeedSupplier leftSpeed, SpeedSupplier rightSpeed) {
		requires(drivetrain);
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
	}

	protected void initialize() {
	}

	protected void execute() {
		drivetrain.twoJoysticksDriving(leftSpeed.getSpeed(), rightSpeed.getSpeed());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		drivetrain.stop();
	}

	protected void interrupted() {
		end();
	}
}
