package com.spikes2212.robot2016.subsystems;

import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.vision.USBCamera;

public class CameraController {

	private USBCamera camera;
	private boolean on;

	public CameraController(String name) {
		this.camera = new USBCamera(name);
	}

	public void start() {
		camera.startCapture();
		on = true;
	}

	public void stop() {
		on = false;
		camera.stopCapture();
	}

	public void getImage(Image image) {
		camera.getImage(image);
	}

	public boolean isOn() {
		return on;
	}
}
