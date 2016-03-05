package com.spikes2212.robot2016.commands.triz;

import com.spikes2212.robot2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StopTriz extends Command {

	public StopTriz() {
		requires(Robot.triz);
	}

	@Override
	protected void initialize() {
		Robot.triz.stop();
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
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
