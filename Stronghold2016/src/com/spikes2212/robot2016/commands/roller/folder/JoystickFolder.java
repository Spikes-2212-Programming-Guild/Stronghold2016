package com.spikes2212.robot2016.commands.roller.folder;

import edu.wpi.first.wpilibj.command.Command;
import static com.spikes2212.robot2016.Robot.*;

/**
 *
 */
public class JoystickFolder extends Command {

	public JoystickFolder() {
		requires(folder);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		folder.moveFolder(oi.leftNavigator.getY());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		folder.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
