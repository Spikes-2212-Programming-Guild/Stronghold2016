package com.spikes2212.robot2016.commands.camera;

import static com.spikes2212.robot2016.Robot.cameras;

import java.util.function.Consumer;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.command.Command;

public class UseFrontImage extends Command {

	private Image image;
	private Consumer<Image> consumer;

	public UseFrontImage(Consumer<Image> consumer) {
		requires(cameras);
		image = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		this.consumer = consumer;
	}

	@Override
	protected void initialize() {
		cameras.startFront();
	}

	@Override
	protected void execute() {
		cameras.getImage(image);
		consumer.accept(image);
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
