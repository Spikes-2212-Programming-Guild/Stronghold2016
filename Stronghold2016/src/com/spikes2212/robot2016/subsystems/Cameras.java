package com.spikes2212.robot2016.subsystems;

import java.util.Optional;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.spikes2212.robot2016.Constants.Vision;

import edu.wpi.first.wpilibj.CameraServer;
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

	public void stream(Image image) {
		CameraServer.getInstance().setImage(image);
	}

	@Override
	protected void initDefaultCommand() {
	}

	public Optional<Double> getDistanceFromTower(Image image, Image binary) {
		getImage(image);
		NIVision.imaqColorThreshold(binary, image, 255, NIVision.ColorMode.RGB, Vision.rRange, Vision.gRange,
				Vision.bRange);
		NIVision.imaqParticleFilter4(binary, binary, Vision.criteria, Vision.options, null);
		int count = NIVision.imaqCountParticles(binary, 1);
		double maxArea = 0;
		int maxIndex = 0;
		for (int index = 0; index < count; index++) {
			double area = NIVision.imaqMeasureParticle(image, index, 0, NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
			maxIndex = area > maxArea ? index : maxIndex;
		}
		if (count != 0) {
			double width = NIVision.imaqMeasureParticle(image, maxIndex, 0,
					NIVision.MeasurementType.MT_BOUNDING_RECT_WIDTH);
			double viewWidth = Vision.RESOLUTION_WIDTH / width * Vision.REFLECTIVE_WIDTH;
			double distance = viewWidth / (2 * Math.tan(Math.toRadians(Vision.VIEW_HORIZONTAL_ANGLE)));
			return Optional.of(distance);
		}
		return Optional.empty();

	}

}
