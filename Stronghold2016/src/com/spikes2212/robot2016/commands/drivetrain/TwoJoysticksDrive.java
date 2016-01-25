package com.spikes2212.robot2016.commands.drivetrain;

import static com.spikes2212.robot2016.Robot.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TwoJoysticksDrive extends Command {

	public TwoJoysticksDrive() {
		requires(drivetrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		drivetrain.twoJoysticksDriving(oi.leftDriver.getY(), oi.rightDriver.getY());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		drivetrain.stop();
	}

	protected void interrupted() {
		end();
	}
}