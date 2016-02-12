package com.spikes2212.robot2016.subsystems;

import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.vision.USBCamera;

public class CameraController {

	private USBCamera camera;
	private boolean on;

	public CameraController(String name) {
		try {
			this.camera = new USBCamera(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		try {
			camera.startCapture();
			on = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		on = false;
		try {
			camera.stopCapture();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getImage(Image image) {
		try {
			camera.getImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isOn() {
		return on;
	}
}
