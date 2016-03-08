package com.spikes2212.robot2016.util;

import java.util.Optional;

import com.ni.vision.NIVision.Image;
import com.spikes2212.robot2016.Constants;

import edu.wpi.first.wpilibj.vision.USBCamera;

public class CameraController {

	private Optional<USBCamera> camera;
	private boolean on;

	public CameraController(String name) {
		try {
			USBCamera camera = new USBCamera(name);
			camera.setFPS(Constants.FPS);
			camera.setSize(Constants.RESOLUTION_WIDTH, Constants.RESOLUTION_HEIGHT);
			camera.setExposureAuto();
			camera.updateSettings();
			this.camera = Optional.of(camera);
		} catch (Exception e) {
			e.printStackTrace();
			this.camera = Optional.empty();
		}
	}

	public void start() {
		if (camera.isPresent()) {
			try {
				camera.get().startCapture();
				on = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void stop() {
		try {
			camera.ifPresent(USBCamera::stopCapture);
		} catch (Exception e) {
			e.printStackTrace();
		}
		on = false;
	}

	public void getImage(Image image) {
		try {
			camera.ifPresent(c -> c.getImage(image));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean hasCamera() {
		return camera.isPresent();
	}

	public boolean isOn() {
		return on;
	}
}
