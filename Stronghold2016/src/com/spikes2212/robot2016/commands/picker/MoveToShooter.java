package com.spikes2212.robot2016.commands.picker;

import static com.spikes2212.robot2016.Robot.picker;

import com.spikes2212.robot2016.Constants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveToShooter extends Command {

	public MoveToShooter() {
		requires(picker);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!isFinished()) {
			picker.roll(Constants.PICKER_ROLL_OUT_SPEED);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !picker.isBoulderInside();
	}

	// Called once after isFinished returns true
	protected void end() {
		picker.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}