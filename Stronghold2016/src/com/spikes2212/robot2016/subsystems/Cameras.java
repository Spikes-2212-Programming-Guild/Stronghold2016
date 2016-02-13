package com.spikes2212.robot2016.subsystems;

import java.util.Optional;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Cameras extends Subsystem {

	private Image image;

	private CameraController front, rear;

	public Cameras(String frontName, String rearName) {
		this.front = new CameraController(frontName);
		this.rear = new CameraController(rearName);
		image = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
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

	public void getImage() {
		if (front.isOn()) {
			front.getImage(image);
		} else if (rear.isOn()) {
			rear.getImage(image);
		}
	}

	public void stream() {
		CameraServer.getInstance().setImage(image);
	}

	public Optional<Double> getDistanceFromTower() {
		return Optional.empty();
	}

	@Override
	protected void initDefaultCommand() {
	}

}
