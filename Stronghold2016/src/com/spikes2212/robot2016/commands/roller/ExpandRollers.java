package com.spikes2212.robot2016.commands.roller;

import com.spikes2212.robot2016.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExpandRollers extends Command {

	Joystick navigator = Robot.oi.navigator;

	public ExpandRollers() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.roller);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.roller.canExpand()) {
			Robot.roller.fold(navigator.getX());
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.roller.canExpand();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
