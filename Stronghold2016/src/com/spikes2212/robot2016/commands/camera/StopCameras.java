package com.spikes2212.robot2016.commands.camera;

import static com.spikes2212.robot2016.Robot.vision;

import edu.wpi.first.wpilibj.command.Command;

public class StopCameras extends Command {

	public StopCameras() {
		requires(vision);
	}

	@Override
	protected void initialize() {
		new Thread(() -> vision.stop()).start();
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
