package com.spikes2212.robot2016.commands.drivetrain;

import static com.spikes2212.robot2016.Robot.drivetrain;

import com.spikes2212.robot2016.util.SpeedSupplier;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickForwardDrive extends Command {

	private SpeedSupplier forwardSpeed;

	public JoystickForwardDrive(SpeedSupplier forwardSpeed) {
		requires(drivetrain);
		this.forwardSpeed = forwardSpeed;
	}

	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		drivetrain.forward(forwardSpeed.getSpeed());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		drivetrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
