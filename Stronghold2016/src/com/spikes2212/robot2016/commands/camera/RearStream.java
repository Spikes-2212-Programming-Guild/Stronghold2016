package com.spikes2212.robot2016.commands.camera;

import static com.spikes2212.robot2016.Robot.vision;

import edu.wpi.first.wpilibj.command.Command;

public class RearStream extends Command {

	public RearStream() {
		requires(vision);
	}

	@Override
	protected void initialize() {
		new Thread(() -> vision.startRear()).start();
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
