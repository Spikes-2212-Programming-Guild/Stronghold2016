package com.spikes2212.robot2016.commands.folder;

import static com.spikes2212.robot2016.Robot.folder;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopFolder extends Command {

	public StopFolder() {
		requires(folder);
	}

	protected void initialize() {
		folder.stop();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {

	}

	protected void interrupted() {
		end();
	}
}
