package com.spikes2212.robot2016.commands.shooter;

import static com.spikes2212.robot2016.Robot.shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopShooter extends Command {

	public StopShooter() {
		requires(shooter);
	}

	protected void initialize() {
		shooter.stop();
	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		shooter.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
