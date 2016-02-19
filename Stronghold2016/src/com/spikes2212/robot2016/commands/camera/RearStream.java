package com.spikes2212.robot2016.commands.camera;

import static com.spikes2212.robot2016.Robot.vision;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.command.Command;

public class RearStream extends Command {

	private Image image;

	public RearStream() {
		requires(vision);
	}

	@Override
	protected void initialize() {
		image = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		vision.startRear();
	}

	@Override
	protected void execute() {
		vision.getImage(image);
		vision.stream(image);
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
