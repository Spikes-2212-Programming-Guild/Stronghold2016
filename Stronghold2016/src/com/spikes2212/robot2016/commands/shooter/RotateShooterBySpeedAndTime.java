package com.spikes2212.robot2016.commands.shooter;

import static com.spikes2212.robot2016.Robot.shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateShooterBySpeedAndTime extends Command {

	private double speed;

	public RotateShooterBySpeedAndTime(double speed, double timeout) {
		setTimeout(timeout);
		requires(shooter);
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		shooter.shoot(speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		shooter.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
