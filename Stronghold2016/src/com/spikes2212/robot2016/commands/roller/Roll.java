package com.spikes2212.robot2016.commands.roller;

import static com.spikes2212.robot2016.Robot.roller;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Roll extends Command {
	private Command choice;

	public Roll() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(roller);
	}

	protected void initialize() {
		choice = (roller.hasBoulder()) ? new RollOut() : new RollInside();
	}

	protected void execute() {
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !choice.isRunning();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
