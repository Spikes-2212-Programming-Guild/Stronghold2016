package com.spikes2212.robot2016.commands.camera;

import static com.spikes2212.robot2016.Robot.cameras;

import edu.wpi.first.wpilibj.command.Command;

public class FrontStream extends Command {

	public FrontStream() {
		requires(cameras);
	}

	@Override
	protected void initialize() {
		cameras.startFront();
	}

	@Override
	protected void execute() {
		cameras.getImage();
		cameras.stream();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {
		end();
	}

}
