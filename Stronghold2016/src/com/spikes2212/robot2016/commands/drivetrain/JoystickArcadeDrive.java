package com.spikes2212.robot2016.commands.drivetrain;

import static com.spikes2212.robot2016.Robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickArcadeDrive extends Command {

	public JoystickArcadeDrive() {
		requires(drivetrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		drivetrain.arcade(oi.rightDriver.getY(), oi.rightDriver.getX());
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
