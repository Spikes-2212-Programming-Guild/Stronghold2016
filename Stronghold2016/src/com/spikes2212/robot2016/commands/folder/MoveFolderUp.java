package com.spikes2212.robot2016.commands.folder;

import static com.spikes2212.robot2016.Robot.folder;

import com.spikes2212.robot2016.Constants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveFolderUp extends Command {

	public MoveFolderUp() {
		requires(folder);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		folder.calibrate();
	}

	protected void execute() {
		if (!isFinished()) {
			folder.tryMove(-Constants.FOLDER_UP_SPEED);
		}
	}

	protected boolean isFinished() {
		return folder.isUp();
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
