package com.spikes2212.robot2016.commands.picker;

import static com.spikes2212.robot2016.Robot.picker;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopRoll extends Command {

	public StopRoll() {
		requires(picker);
	}

	protected void initialize() {
		picker.stop();
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
