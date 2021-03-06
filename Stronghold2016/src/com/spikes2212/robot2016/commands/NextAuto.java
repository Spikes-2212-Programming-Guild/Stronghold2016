package com.spikes2212.robot2016.commands;

import edu.wpi.first.wpilibj.command.Command;
import static com.spikes2212.robot2016.Robot.selected;
import static com.spikes2212.robot2016.Robot.commands;;

/**
 *
 */
public class NextAuto extends Command {

	public NextAuto() {
		setRunWhenDisabled(true);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		if (selected < commands.length - 1) {
			selected++;
		}
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
