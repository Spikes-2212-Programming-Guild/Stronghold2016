package com.spikes2212.robot2016.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.ColorMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.NIVision.MeasurementType;
import com.ni.vision.NIVision.ROI;
import com.ni.vision.NIVision.Range;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Cameras extends Subsystem {

	private Image image, binary;

	private CameraController front, rear;
	
	double MIN_WIDTH=0;
	int CAMERA_Y_ANGLE=59;
	int CAMERA_X_ANGLE=59;
	int CAMERA_ANGLE=0;
	int Y_RESOLUTION=480;
	int TARGET_HIGHT=0;
	int rMin, gMin,bMin,rMax,gMax,bMax;
	double Y_ANGLE_PER_PIXEL=CAMERA_Y_ANGLE/Y_RESOLUTION;
	int X_RESOLUTION=0;
	int X_ANGLE_PER_PIXEL=CAMERA_X_ANGLE/X_RESOLUTION;
	NIVision.ParticleFilterCriteria2 [] criteria=new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 option=new NIVision.ParticleFilterOptions2(0,0,0,100);
	
	
	public Cameras(String frontName, String rearName) {
		this.front = new CameraController(frontName);
		this.rear = new CameraController(rearName);
		image = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		binary = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		criteria[0]= new NIVision.ParticleFilterCriteria2(MeasurementType.MT_BOUNDING_RECT_WIDTH,MIN_WIDTH,X_RESOLUTION,0,0);
		rMin=0;
		gMin=0;
		bMin=0;
		rMax=0;
		gMax=0;
		bMax=0;
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

	public void getImage() {
		if (front.isOn()) {
			front.getImage(image);
		} else if (rear.isOn()) {
			rear.getImage(image);
		}
	}

	public void stream() {
		CameraServer.getInstance().setImage(image);
	}

	@Override
	protected void initDefaultCommand() {
	}
	
	public double getAngleUp() {
		getImage();
		NIVision.imaqColorThreshold(binary, image, 255, ColorMode.RGB  , new Range(rMin,rMax), new Range(gMin,gMax), new Range(bMin,bMax));
		NIVision.imaqParticleFilter4(binary, binary, criteria, option, null);
		int counter=NIVision.imaqCountParticles(binary, 0);
		int maxIndex=0;
		double width,maxWidth=0;
		for(int i=0;i<counter;i++){
			width=NIVision.imaqMeasureParticle(binary, i, 0, MeasurementType.MT_BOUNDING_RECT_WIDTH);
			if(width>maxWidth){
				maxIndex=i;
				maxWidth=width;
			}
		}
		double angle=0;
		if(counter!=0){
			double hight=Y_RESOLUTION-NIVision.imaqMeasureParticle(binary, maxIndex, 0, MeasurementType.MT_BOUNDING_RECT_TOP);
			angle=(hight*Y_ANGLE_PER_PIXEL+CAMERA_ANGLE);
			return angle;
		}
		return 0;
	}
	public double getDistanceFromTower() {
		double angleUp = getAngleUp();
		double distance= TARGET_HIGHT/Math.tan(Math.toRadians(angleUp));
		return distance;
	}

}
