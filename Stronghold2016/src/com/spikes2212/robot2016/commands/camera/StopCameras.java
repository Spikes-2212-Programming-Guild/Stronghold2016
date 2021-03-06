package com.spikes2212.robot2016.commands.camera;

import static com.spikes2212.robot2016.Robot.vision;

import com.spikes2212.robot2016.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;

public class StopCameras extends Command {

	public StopCameras() {
		requires(vision);
		setRunWhenDisabled(true);
	}

	@Override
	protected void initialize() {
		new Thread(() -> vision.setCamera(Vision.CameraType.NONE)).start();
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
