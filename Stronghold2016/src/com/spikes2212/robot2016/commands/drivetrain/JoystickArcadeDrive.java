package com.spikes2212.robot2016.commands.drivetrain;

import static com.spikes2212.robot2016.Robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickArcadeDrive extends Command {

	private SpeedSupplier straightSpeed;
	private SpeedSupplier turnSpeed;

	public JoystickArcadeDrive(SpeedSupplier straightSpeed, SpeedSupplier turnSpeed) {
		requires(drivetrain);
		this.straightSpeed = straightSpeed;
		this.turnSpeed = turnSpeed;
	}

	protected void initialize() {
	}

	protected void execute() {
		drivetrain.arcade(straightSpeed.getSpeed(), turnSpeed.getSpeed());
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
