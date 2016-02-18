package com.spikes2212.robot2016.commands.folder;

import static com.spikes2212.robot2016.Robot.folder;

import com.spikes2212.robot2016.Constants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveFolderDown extends Command {

	public MoveFolderDown() {
		requires(folder);
	}

	protected void initialize() {
		folder.calibrate();
	}

	protected void execute() {
		if (!isFinished()) {
			folder.tryMove(Constants.FOLDER_DOWN_SPEED);
		}
	}

	protected boolean isFinished() {
		return folder.isDown();
	}

	protected void end() {
		folder.stop();
	}

	protected void interrupted() {
		end();
	}
}
