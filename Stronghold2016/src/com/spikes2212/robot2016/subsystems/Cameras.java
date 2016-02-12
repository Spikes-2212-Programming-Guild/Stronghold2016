package com.spikes2212.robot2016.subsystems;

import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Cameras extends Subsystem {

	private CameraController front, rear;

	public Cameras(String frontName, String rearName) {
		this.front = new CameraController(frontName);
		this.rear = new CameraController(rearName);
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

	@Override
	protected void initDefaultCommand() {
	}

}
