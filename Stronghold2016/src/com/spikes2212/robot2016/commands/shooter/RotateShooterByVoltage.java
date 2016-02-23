package com.spikes2212.robot2016.commands.shooter;

import static com.spikes2212.robot2016.Robot.shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateShooterByVoltage extends Command {

	private double voltage;

	public RotateShooterByVoltage(double voltage) {
		requires(shooter);
		this.voltage = voltage;
	}

	protected void initialize() {
	}

	protected void execute() {
		shooter.shoot(voltage);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		shooter.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
