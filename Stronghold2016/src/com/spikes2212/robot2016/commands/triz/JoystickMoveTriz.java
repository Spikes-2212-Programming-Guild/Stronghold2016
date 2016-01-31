package com.spikes2212.robot2016.commands.triz;

import static com.spikes2212.robot2016.Robot.triz;

import com.spikes2212.robot2016.util.SpeedSupplier;

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

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		triz.moveTriz(speed.getSpeed());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return triz.isUp() || triz.isDown();
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
