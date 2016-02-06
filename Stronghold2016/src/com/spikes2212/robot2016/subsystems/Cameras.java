package com.spikes2212.robot2016.subsystems;

import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class Cameras extends Subsystem {

	private USBCamera front, rear;
	private boolean frontOn, rearOn;

	public Cameras(USBCamera front, USBCamera rear) {
		this.front = front;
		this.rear = rear;
	}

	public Cameras(String frontName, String rearName) {
		this(new USBCamera(frontName), new USBCamera(rearName));
	}

	public boolean isFrontOn() {
		return frontOn;
	}

	public boolean isRearOn() {
		return rearOn;
	}

	public void startFront() {
		rear.stopCapture();
		front.startCapture();
		frontOn = true;
	}

	public void startRear() {
		front.stopCapture();
		rear.startCapture();
		rearOn = true;
	}

	public void stop() {
		front.stopCapture();
		rear.stopCapture();
		frontOn = false;
		rearOn = false;
	}

	public void getImage(Image image) {
		if (frontOn) {
			front.getImage(image);
		} else if (rearOn) {
			rear.getImage(image);
		}
	}

	@Override
	protected void initDefaultCommand() {
	}

}
