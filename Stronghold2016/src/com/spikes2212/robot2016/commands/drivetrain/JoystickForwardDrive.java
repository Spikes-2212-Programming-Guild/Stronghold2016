package com.spikes2212.robot2016.commands.drivetrain;

import static com.spikes2212.robot2016.Robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickForwardDrive extends Command {

	private SpeedSupplier forwardSpeed;

	public JoystickForwardDrive(SpeedSupplier forwardSpeed) {
		requires(drivetrain);
		this.forwardSpeed = forwardSpeed;
	}

	protected void initialize() {
	}

	protected void execute() {
		drivetrain.forward(forwardSpeed.getSpeed());
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
