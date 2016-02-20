package com.spikes2212.robot2016.commands.triz;

import static com.spikes2212.robot2016.Robot.triz;

import com.spikes2212.robot2016.Constants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveTrizUp extends Command {

	public MoveTrizUp() {
		requires(triz);
	}

	protected void initialize() {
	}

	protected void execute() {
		if (!triz.isUp()) {
			triz.tryMove(-Constants.TRIZ_UP_SPEED);
		}
	}

	protected boolean isFinished() {
		return triz.isUp();
	}

	protected void end() {
		triz.stop();
	}

	protected void interrupted() {
		end();
	}
}
