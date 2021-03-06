package com.spikes2212.robot2016.commands.drivetrain;

import com.spikes2212.robot2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnDriveBySpeedAndTime extends Command {

	private double speed;

	public TurnDriveBySpeedAndTime(double speed, double time) {
		super(time);
		requires(Robot.drivetrain);
		this.speed = speed;
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		Robot.drivetrain.turn(speed);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		Robot.drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
