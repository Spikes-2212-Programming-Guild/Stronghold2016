package com.spikes2212.robot2016.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class Cameras extends Subsystem {

	private USBCamera front, rear;
	private boolean frontOn, rearOn;
	private final double VIEW_ANGLE_UP = 59;
	private final double VIEW_ANGLE_SIDE = 37;
	private final double CAMERA_ANGLE = 59;
	private final double TARGET_HEIGHT = 59;
	private final double CAMERA_RESOLUTION_Y = 240;
	private final double CAMERA_RESOLUTION_X = 320;
	private final double Y_ANGLE_PER_PIXEL = VIEW_ANGLE_UP / CAMERA_RESOLUTION_Y;
	private final double X_ANGLE_PER_PIXEL = VIEW_ANGLE_SIDE / CAMERA_RESOLUTION_X;
	public final double WIDTH_MIN = 0;
	

	public final NIVision.Range rRange = new NIVision.Range(230, 255);
	public final NIVision.Range gRange = new NIVision.Range(250, 255);
	public final NIVision.Range bRange = new NIVision.Range(240, 255);

	public final NIVision.ParticleFilterOptions2 options = new NIVision.ParticleFilterOptions2(0, 0, 1, 1);
	public final NIVision.ParticleFilterCriteria2[] criteria = { new NIVision.ParticleFilterCriteria2(
			NIVision.MeasurementType.MT_BOUNDING_RECT_WIDTH, WIDTH_MIN, 640, 0, 0) };

	public Cameras(USBCamera front, USBCamera rear) {
		this.front = front;
		this.rear = rear;
	}

	public Cameras(String frontName, String rearName) {
		this.front = new USBCamera(frontName);
		front.setFPS(15);
		SmartDashboard.putNumber("r-min", 0);
		SmartDashboard.putNumber("g-min", 0);
		SmartDashboard.putNumber("b-min", 245);
		SmartDashboard.putNumber("r-max", 255);
		SmartDashboard.putNumber("g-max", 255);
		SmartDashboard.putNumber("b-max", 255);
	}

	public boolean isFrontOn() {
		return frontOn;
	}

	public boolean isRearOn() {
		return rearOn;
	}

	public void startFront() {
		// rear.stopCapture();
		front.startCapture();
		frontOn = true;
		// rearOn = false;
	}

	public void startRear() {
		front.stopCapture();
		// rear.startCapture();
		// rearOn = true;
		frontOn = false;
	}

	public void stop() {
		front.stopCapture();
		// rear.stopCapture();
		frontOn = false;
		// rearOn = false;
	}

	public void getImage(Image image) {
		if (frontOn) {
			front.getImage(image);
		} else if (rearOn) {
			// rear.getImage(image);
		}
	}

	private Image imageGetAngleUp = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
	private Image binaryGetAngleUp = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_U8, 0);

	public double getAngleUp() {
		// NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		getImage(imageGetAngleUp);
		// binaryGetAngleUp =
		// NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_U8, 0);
		NIVision.imaqColorThreshold(binaryGetAngleUp, imageGetAngleUp, 255, NIVision.ColorMode.RGB, rRange, gRange,
				bRange);
		NIVision.imaqParticleFilter4(binaryGetAngleUp, binaryGetAngleUp, criteria, options, null);
		int count = NIVision.imaqCountParticles(binaryGetAngleUp, 1);
		double maxArea = 0;
		int maxIndex = 0;
		for (int index = 0; index < count; index++) {
			double area = NIVision.imaqMeasureParticle(imageGetAngleUp, index, 0,
					NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
			maxIndex = area > maxArea ? index : maxIndex;
		}
		if (count != 0) {
			double height = NIVision.imaqMeasureParticle(imageGetAngleUp, maxIndex, 0,
					NIVision.MeasurementType.MT_FIRST_PIXEL_Y);
			return CAMERA_ANGLE + Y_ANGLE_PER_PIXEL * height;
		}
		return -1;
	}

	private Image imageGetAngleHorizontal = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
	private Image binaryGetAngleHorizontal = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_U8, 0);

	public double getAngleHorizontal() {
		// image = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		getImage(imageGetAngleHorizontal);
		rRange.minValue = (int) SmartDashboard.getNumber("r-min", 0);
		gRange.minValue = (int) SmartDashboard.getNumber("g-min", 0);
		bRange.minValue = (int) SmartDashboard.getNumber("b-min", 0);
		rRange.maxValue = (int) SmartDashboard.getNumber("r-max", 0);
		gRange.maxValue = (int) SmartDashboard.getNumber("g-max", 0);
		bRange.maxValue = (int) SmartDashboard.getNumber("b-max", 0);
		// binary = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_U8, 0);
		NIVision.imaqColorThreshold(binaryGetAngleHorizontal, imageGetAngleHorizontal, 255, NIVision.ColorMode.RGB,
				rRange, gRange, bRange);
		CameraServer.getInstance().setImage(binaryGetAngleHorizontal);
		NIVision.imaqParticleFilter4(binaryGetAngleHorizontal, binaryGetAngleHorizontal, criteria, options, null);
		int count = NIVision.imaqCountParticles(binaryGetAngleHorizontal, 1);
		double maxArea = 0;
		int maxIndex = 0;
		for (int index = 0; index < count; index++) {
			double area = NIVision.imaqMeasureParticle(binaryGetAngleHorizontal, index, 0,
					NIVision.MeasurementType.MT_BOUNDING_RECT_WIDTH);
			maxIndex = area > maxArea ? index : maxIndex;
		}
		if (count != 0) {
			double width = NIVision.imaqMeasureParticle(binaryGetAngleHorizontal, maxIndex, 0,
					NIVision.MeasurementType.MT_BOUNDING_RECT_WIDTH);
			return X_ANGLE_PER_PIXEL * width;
		}
		return -1;
	}
	public void updateCon(){
		
	}
	public double getDistance() {
		double angleUp = getAngleUp();
		return TARGET_HEIGHT / Math.tan(Math.toRadians(angleUp + CAMERA_ANGLE));
	}

	@Override
	protected void initDefaultCommand() {
	}

}
