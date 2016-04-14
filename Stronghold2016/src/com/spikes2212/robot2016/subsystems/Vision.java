package com.spikes2212.robot2016.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.spikes2212.robot2016.util.CameraController;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem {
	
	public enum CameraType { FRONT, REAR, NONE }
	
	private CameraController front, rear;
	private Image image;
	private CameraType type = CameraType.NONE;

	public Vision(String frontName, String rearName) {
		this.front = new CameraController(frontName);
		this.rear = new CameraController(rearName);
		this.image = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
	}

	public synchronized boolean isFrontOn() {
		return front.isOn();
	}

	public synchronized boolean isRearOn() {
		return rear.isOn();
	}
	
	public synchronized void setCamera(CameraType type) {
		this.type = type;
	}

	public synchronized void start() {
		front.start();
		rear.start();
	}

	public synchronized void stop() {
		front.stop();
		rear.stop();
	}

	public synchronized void tryStream() {
		try {
			if (type == CameraType.FRONT) {
				front.getImage(image);
				CameraServer.getInstance().setImage(image);
			} else if (type == CameraType.REAR) {
				rear.getImage(image);
				CameraServer.getInstance().setImage(image);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initDefaultCommand() {
	}

}
