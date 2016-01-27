package com.spikes2212.robot2016.commands.roller.picker;

import static com.spikes2212.robot2016.Robot.picker;

import com.spikes2212.robot2016.Permanents;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RollBallIn extends Command {

	public RollBallIn() {
		requires(picker);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (picker.isBallInside()) {
			end();
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		picker.roll(Permanents.PICKER_ROLL_IN_SPEED);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return picker.isBallInside();
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
