package com.spikes2212.robot2016.commands.drivetrain;

import com.spikes2212.robot2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StraightDriveBySpeedAndTime extends Command {

	private double speed;
	
	public StraightDriveBySpeedAndTime(double speed, double time) {
		super(time);
		requires(Robot.drivetrain);
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		Robot.drivetrain.forward(speed);
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
