package com.spikes2212.robot2016.commands.roller.picker;

import static com.spikes2212.robot2016.Robot.picker;

import com.spikes2212.robot2016.Permanents;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RollOut extends Command {

	public static final double TIMEOUT = 2;
	
	public RollOut() {
		super(TIMEOUT);
		requires(picker);
	}

	protected void initialize() {
	}

	protected void execute() {
		picker.roll(-Permanents.PICKER_ROLL_OUT_SPEED);
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		picker.stop();
	}

	protected void interrupted() {
		end();
	}
}
