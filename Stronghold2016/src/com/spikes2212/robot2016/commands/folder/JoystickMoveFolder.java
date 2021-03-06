package com.spikes2212.robot2016.commands.folder;

import static com.spikes2212.robot2016.Robot.folder;

import com.spikes2212.robot2016.commands.drivetrain.SpeedSupplier;

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
		folder.calibrate();
	}

	protected void execute() {
		double speed = this.speed.getSpeed();
		folder.tryMove(speed);
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
