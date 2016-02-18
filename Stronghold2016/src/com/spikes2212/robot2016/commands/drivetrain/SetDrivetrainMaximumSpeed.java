package com.spikes2212.robot2016.commands.drivetrain;

import com.spikes2212.robot2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetDrivetrainMaximumSpeed extends Command {

	private double maxSpeed;

	public SetDrivetrainMaximumSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	@Override
	protected void initialize() {
		Robot.drivetrain.setMaximumSpeed(maxSpeed);
	}

	@Override
	protected void execute() {

	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {

	}

}
