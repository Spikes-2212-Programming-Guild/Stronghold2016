package com.spikes2212.robot2016.commands.camera;

import static com.spikes2212.robot2016.Robot.cameras;

import edu.wpi.first.wpilibj.command.Command;

public class StopCameras extends Command {

	public StopCameras() {
		requires(cameras);
	}

	@Override
	protected void initialize() {
		cameras.stop();
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
		end();
	}

}
