package com.spikes2212.robot2016.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.spikes2212.robot2016.commands.camera.UseRearImage;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class Cameras extends Subsystem {

	private USBCamera front, rear;
	private boolean frontOn, rearOn;
	private final double VIEW_ANGLE_UP = 37.1;
	private double CAMERA_ANGLE = 21;
	private double TARGET_HEIGHT = 80;
	private final double CAMERA_RESOLUTION_Y = 480;
	private final double Y_ANGLE_PER_PIXEL = VIEW_ANGLE_UP / CAMERA_RESOLUTION_Y;
	public double AREA_MIN = 1;

	public NIVision.Range rRange = new NIVision.Range(0, 255);
	public NIVision.Range gRange = new NIVision.Range(220, 255);
	public NIVision.Range bRange = new NIVision.Range(0, 255);

	NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(
			0, 0, 1, 1);
	
	public Cameras(USBCamera front, USBCamera rear) {
		this.front = front;
		this.rear = rear;
		SmartDashboard.putNumber("rRange min", rRange.minValue);
		SmartDashboard.putNumber("rRange max", rRange.maxValue);
		SmartDashboard.putNumber("gRange min", gRange.minValue);
		SmartDashboard.putNumber("gRange max", gRange.maxValue);
		SmartDashboard.putNumber("bRange min", bRange.minValue);
		SmartDashboard.putNumber("bRange max", bRange.maxValue);
		SmartDashboard.putNumber("Area min", AREA_MIN);
		SmartDashboard.putNumber("Camera angle", CAMERA_ANGLE);
		SmartDashboard.putNumber("Target height", TARGET_HEIGHT);
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
		rearOn = false;
	}

	public void startRear() {
		front.stopCapture();
		rear.startCapture();
		rearOn = true;
		frontOn = false;
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
	
	public void updateConstants(){
		rRange = new NIVision.Range((int)SmartDashboard.getNumber("rRange min"), (int)SmartDashboard.getNumber("rRange max"));
		gRange = new NIVision.Range((int)SmartDashboard.getNumber("gRange min"), (int)SmartDashboard.getNumber("gRange max"));
		bRange = new NIVision.Range((int)SmartDashboard.getNumber("bRange min"), (int)SmartDashboard.getNumber("bRange max"));
		AREA_MIN = SmartDashboard.getNumber("Area min");
		CAMERA_ANGLE = SmartDashboard.getNumber("Camera angle");
		TARGET_HEIGHT = SmartDashboard.getNumber("Target height");
	}

	private Image image = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
	private Image binary = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_U8, 0);
	public double getAngleUp() {
		updateConstants();
		getImage(image);
		criteria[0] = new NIVision.ParticleFilterCriteria2(
				NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MIN,
				100.0, 0, 0);
		NIVision.imaqColorThreshold(binary, image, 255, NIVision.ColorMode.RGB, rRange, gRange, bRange);
		criteria[0].lower = 5;
		int imaqError = NIVision.imaqParticleFilter4(binary, binary,
				criteria, filterOptions, null);
		int count = NIVision.imaqCountParticles(binary, 1);
		SmartDashboard.putNumber("count", count);
		double maxArea = 0;
		int maxIndex = 0;
		for (int index = 0; index < count; index++) {
			double area = NIVision.imaqMeasureParticle(binary, index, 0, NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
			maxIndex = area > maxArea ? index : maxIndex;
			maxArea = area > maxArea ? area : maxArea;
			SmartDashboard.putNumber("area", area);
			SmartDashboard.putNumber("max index", maxIndex);
			SmartDashboard.putNumber("max area", maxArea);
		}
		if (count != 0) {
			double height =CAMERA_RESOLUTION_Y -  NIVision.imaqMeasureParticle(binary, maxIndex, 0, NIVision.MeasurementType.MT_FIRST_PIXEL_Y);
			SmartDashboard.putNumber("height (PX)", height);
			SmartDashboard.putNumber("angle", CAMERA_ANGLE + Y_ANGLE_PER_PIXEL * height);
			return CAMERA_ANGLE + Y_ANGLE_PER_PIXEL * height;
		}
		return 50;// TODO: find what to return here
	}

	public double getDistance() {
		double angleUp = getAngleUp();
		SmartDashboard.putNumber("distance", TARGET_HEIGHT / Math.tan(Math.toRadians(angleUp + CAMERA_ANGLE)));
		return TARGET_HEIGHT / Math.tan(Math.toRadians(angleUp + CAMERA_ANGLE));
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UseRearImage(CameraServer.getInstance()::setImage));
	}

}
