package com.spikes2212.robot2016.commands.triz;

import static com.spikes2212.robot2016.Robot.triz;

import com.spikes2212.robot2016.consts.TrizConstants;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Itamar
 */
public class OpenTriz extends Command {

	public OpenTriz() {
		requires(triz);
	}

	protected void initialize() {

	}

	protected void execute() {
		triz.unfold(TrizConstants.TRIZ_UNFOLD_SPEED);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return triz.canUnfold();
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
