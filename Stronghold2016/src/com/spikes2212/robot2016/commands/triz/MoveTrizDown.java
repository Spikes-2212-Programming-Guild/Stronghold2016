package com.spikes2212.robot2016.commands.triz;

import static com.spikes2212.robot2016.Robot.triz;

import com.spikes2212.robot2016.Permanents;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveTrizDown extends Command {

	public MoveTrizDown() {
		requires(triz);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!triz.isDown()) {
			triz.moveTriz(Permanents.TRIZ_SPEED);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return triz.isDown();
	}

	// Called once after isFinished returns true
	protected void end() {
		triz.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
