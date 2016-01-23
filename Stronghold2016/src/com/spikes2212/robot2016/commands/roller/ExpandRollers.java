package com.spikes2212.robot2016.commands.roller;

import com.spikes2212.robot2016.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Itamar
 */
public class ExpandRollers extends Command {

	Joystick navigator = Robot.oi.navigator;

	public ExpandRollers() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.folder);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.folder.canUnfold()) {
			Robot.folder.fold(navigator.getX());
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.folder.canUnfold();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
