package com.spikes2212.robot2016.commands.camera;

import static com.spikes2212.robot2016.Robot.cameras;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

public class DynamicStream extends Command {

	private Image image;

	public DynamicStream() {
		requires(cameras);
		image = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
	}

	@Override
	protected void initialize() {
		cameras.startDynamic();
	}

	@Override
	protected void execute() {
		cameras.getImage(image);
		CameraServer.getInstance().setImage(image);
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
