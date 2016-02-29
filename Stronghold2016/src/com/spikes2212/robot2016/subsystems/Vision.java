package com.spikes2212.robot2016.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.util.CameraController;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem {

	private CameraController front, rear;
	private Image image;

	public Vision(String frontName, String rearName) {
		this.front = new CameraController(frontName, Constants.EXPOSURE_FRONT);
		this.rear = new CameraController(rearName, Constants.EXPOSURE_REAR);
		this.image = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
	}

	public synchronized boolean isFrontOn() {
		return front.isOn();
	}

	public synchronized boolean isRearOn() {
		return rear.isOn();
	}

	public synchronized void startFront() {
		rear.stop();
		front.start();
	}

	public synchronized void startRear() {
		front.stop();
		rear.start();
	}

	public synchronized void stop() {
		front.stop();
		rear.stop();
	}

	public synchronized void setFrontExposure(int exposure) {
		front.setExposure(exposure);
	}

	public synchronized void setRearExposure(int exposure) {
		rear.setExposure(exposure);
	}

	public synchronized void stream() {
		try {
			if (front.isOn()) {
				front.getImage(image);
				CameraServer.getInstance().setImage(image);
			} else if (rear.isOn()) {
				rear.getImage(image);
				CameraServer.getInstance().setImage(image);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initDefaultCommand() {
	}

}
