package com.spikes2212.robot2016.commands.folder;

import static com.spikes2212.robot2016.Robot.folder;

import com.spikes2212.robot2016.util.SpeedSupplier;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickMoveFolder extends Command {

	private SpeedSupplier speed;

	public JoystickMoveFolder(SpeedSupplier speed) {
		requires(folder);
		this.speed = speed;
	}

	protected void initialize() {
	}

	protected void execute() {
		folder.moveFolder(speed.getSpeed());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		folder.stop();
	}

	protected void interrupted() {
		end();
	}
}
