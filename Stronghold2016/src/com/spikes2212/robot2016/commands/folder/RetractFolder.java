package com.spikes2212.robot2016.commands.folder;

import static com.spikes2212.robot2016.Robot.folder;

import com.spikes2212.robot2016.Constants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RetractFolder extends Command {

	public RetractFolder() {
		requires(folder);
	}

	protected void initialize() {
		folder.calibrate();
	}

	protected void execute() {
		if (!isFinished()) {
			folder.tryMove(Constants.FOLDER_UP_SPEED);
		}
	}

	protected boolean isFinished() {
		return folder.isContracted();
	}

	protected void end() {
		folder.stop();
	}

	protected void interrupted() {
		end();
	}
}
