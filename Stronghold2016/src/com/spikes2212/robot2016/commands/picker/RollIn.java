package com.spikes2212.robot2016.commands.picker;

import static com.spikes2212.robot2016.Robot.picker;

import com.spikes2212.robot2016.Constants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RollIn extends Command {

	public RollIn() {
		requires(picker);
	}

	protected void initialize() {
	}

	protected void execute() {
		picker.roll(Constants.PICKER_ROLL_IN_SPEED);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		picker.stop();
	}

	protected void interrupted() {
		end();
	}
}
