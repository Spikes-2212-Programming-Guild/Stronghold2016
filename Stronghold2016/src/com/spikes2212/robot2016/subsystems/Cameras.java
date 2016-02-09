package com.spikes2212.robot2016.subsystems;

import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.USBCamera;
import com.spikes2212.robot2016.Constants;

public class Cameras extends Subsystem {

	private USBCamera dynamic, imageProcessing;
	private boolean dynamicOn, imageProcessingOn;
	

	public Cameras(USBCamera dynamic, USBCamera imageProcessing) {
		this.dynamic = dynamic;
		this.imageProcessing = imageProcessing;
		dynamic.setBrightness(Constants.CAMERA_DYNAMIC_BRIGHTNESS);
		dynamic.setSize(Constants.CAMERA_DYNAMIC_SIZE_WIDTH,Constants.CAMERA_DYNAMIC_SIZE_HEIGHT);
		dynamic.setFPS(Constants.CAMERA_DYNAMIC_FPS);
		imageProcessing.setBrightness(Constants.CAMERA_IMAGEPROCESSING_BRIGHTNESS);
		imageProcessing.setSize(Constants.CAMERA_IMAGEPROCESSING_SIZE_WIDTH,Constants.CAMERA_IMAGEPROCESSING_SIZE_HEIGHT);
		imageProcessing.setFPS(Constants.CAMERA_IMAGEPROCESSING_FPS);
	}

	public Cameras(String dynamicName, String imageProcessingName) {
		this(new USBCamera(dynamicName), new USBCamera(imageProcessingName));
	}

	public boolean isDynamicOn() {
		return dynamicOn;
	}

	public boolean isImageProcessingOn() {
		return imageProcessingOn;
	}

	public void startDynamic() {
		imageProcessing.stopCapture();
		dynamic.startCapture();
		dynamicOn = true;
		imageProcessingOn = false;
	}

	public void startImageProcessing() {
		dynamic.stopCapture();
		imageProcessing.startCapture();
		imageProcessingOn = true;
		dynamicOn = false;
	}

	public void stop() {
		dynamic.stopCapture();
		imageProcessing.stopCapture();
		dynamicOn = false;
		imageProcessingOn = false;
	}

	public void getImage(Image image) {
		if (dynamicOn) {
			dynamic.getImage(image);
		} else if (imageProcessingOn) {
			imageProcessing.getImage(image);
		}
	}

	@Override
	protected void initDefaultCommand()  {
	}

}
