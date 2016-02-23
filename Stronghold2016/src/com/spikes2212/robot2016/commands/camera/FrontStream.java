package com.spikes2212.robot2016.commands.camera;

import static com.spikes2212.robot2016.Robot.vision;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.command.Command;

public class FrontStream extends Command {

	private Image image;
	private boolean running;

	public FrontStream() {
		requires(vision);
	}

	@Override
	protected void initialize() {
		running = true;
		try {
			image = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
			vision.startFront();
		} catch (Exception e) {
			running = false;
			e.printStackTrace();
		}
	}

	@Override
	protected void execute() {
		if (!isFinished()) {
			try {
				vision.getImage(image);
				vision.stream(image);
			} catch (Exception e) {

			}
		}
	}

	@Override
	protected boolean isFinished() {
		return !running;
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {
		end();
	}

}
