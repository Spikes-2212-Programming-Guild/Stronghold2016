package com.spikes2212.robot2016.util;

import java.util.Optional;

import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.vision.USBCamera;

public class CameraController {

	private Optional<USBCamera> camera;
	private boolean on;
	private int exposure;

	public CameraController(String name, int exposure) {
		try {
			USBCamera camera = new USBCamera(name);
			camera.setFPS(15);
			camera.setSize(160, 120);
			camera.setExposureManual(exposure);
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
			on = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public void setExposure(int exposure) {
		if (exposure != this.exposure) {
			try {
				camera.ifPresent(c -> c.setExposureManual(exposure));
				camera.ifPresent(USBCamera::updateSettings);
				this.exposure = exposure;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
