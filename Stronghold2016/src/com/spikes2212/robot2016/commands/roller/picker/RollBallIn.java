package com.spikes2212.robot2016.commands.roller.picker;

import static com.spikes2212.robot2016.Robot.picker;

import com.spikes2212.robot2016.Constants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RollBallIn extends Command {

	public RollBallIn() {
		requires(picker);
	}

	protected void initialize() {
	}

	protected void execute() {
		if (!isFinished()) {
			picker.roll(Constants.PICKER_ROLL_IN_SPEED);
		}
	}

	protected boolean isFinished() {
		return picker.isBallInside();
	}

	protected void end() {
		picker.stop();
	}

	protected void interrupted() {
		end();
	}
}
