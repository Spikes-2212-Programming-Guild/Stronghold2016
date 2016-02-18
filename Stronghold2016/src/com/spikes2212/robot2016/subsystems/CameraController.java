package com.spikes2212.robot2016.subsystems;

import java.util.Optional;

import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.vision.USBCamera;

public class CameraController {

	private Optional<USBCamera> camera;
	private boolean on;

	public CameraController(String name) {
		try {
			USBCamera camera = new USBCamera(name);
			this.camera = Optional.of(camera);
		} catch (Exception e) {
			this.camera = Optional.empty();
		}
	}

	public void start() {
		camera.ifPresent(USBCamera::startCapture);
		on = true;
	}

	public void stop() {
		camera.ifPresent(USBCamera::stopCapture);
		on = false;
	}

	public void getImage(Image image) {
		camera.ifPresent(c -> c.getImage(image));
	}

	public boolean isOn() {
		return on;
	}
}
