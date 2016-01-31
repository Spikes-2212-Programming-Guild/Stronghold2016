package com.spikes2212.robot2016.commands.triz;

import static com.spikes2212.robot2016.Robot.triz;

import com.spikes2212.robot2016.commands.drivetrain.SpeedSupplier;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickMoveTriz extends Command {

	private SpeedSupplier speed;

	public JoystickMoveTriz(SpeedSupplier speed) {
		requires(triz);
		this.speed = speed;
	}

	protected void initialize() {
	}

	protected void execute() {
		double speed = this.speed.getSpeed();
		if (triz.canMove(speed)) {
			triz.move(speed);
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		triz.stop();
	}

	protected void interrupted() {
		end();
	}
}
