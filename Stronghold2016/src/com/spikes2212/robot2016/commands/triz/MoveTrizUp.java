package com.spikes2212.robot2016.commands.triz;

import static com.spikes2212.robot2016.Robot.triz;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveTrizUp extends Command {

	private double speed;

	public MoveTrizUp(double speed) {
		requires(triz);
		this.speed = speed;
	}

	protected void initialize() {
	}

	protected void execute() {
		if (!triz.isUp()) {
			triz.tryMove(-speed);
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
