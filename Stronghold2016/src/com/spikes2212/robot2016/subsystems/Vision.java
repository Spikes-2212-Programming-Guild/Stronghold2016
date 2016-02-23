package com.spikes2212.robot2016.subsystems;

import com.ni.vision.NIVision.Image;
import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.util.CameraController;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem {

	private CameraController front, rear;

	public Vision(String frontName, String rearName) {
		this.front = new CameraController(frontName, Constants.EXPOSURE_FRONT);
		this.rear = new CameraController(rearName, Constants.EXPOSURE_REAR);

	}

	public boolean isFrontOn() {
		return front.isOn();
	}

	public boolean isRearOn() {
		return rear.isOn();
	}

	public void startFront() {
		rear.stop();
		front.start();
	}

	public void startRear() {
		front.stop();
		rear.start();
	}

	public void stop() {
		front.stop();
		rear.stop();
	}

	public void getImage(Image image) {
		if (front.isOn()) {
			front.getImage(image);
		} else if (rear.isOn()) {
			rear.getImage(image);
		}
	}

	public void setFrontExposure(int exposure) {
		front.setExposure(exposure);
	}

	public void setRearExposure(int exposure) {
		rear.setExposure(exposure);
	}

	public void stream(Image image) {
		try {
			CameraServer.getInstance().setImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initDefaultCommand() {
	}

}
