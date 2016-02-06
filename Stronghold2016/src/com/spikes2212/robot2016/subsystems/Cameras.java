package com.spikes2212.robot2016.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.USBCamera;
import java.util.List;

public class Cameras extends Subsystem {

    private USBCamera front, rear;
    private boolean frontOn, rearOn;
    private final double VIEW_ANGLE_UP = 59;
    private final double CAMERA_ANGLE = 59;
    private final double TARGET_HEIGHT = 59;
    private final double CAMERA_RESOLUTION_Y = 480;
    private final double Y_ANGLE_PER_PIXEL = VIEW_ANGLE_UP / CAMERA_RESOLUTION_Y;
    public final double AREA_MIN = 60;

    public final NIVision.Range hRange = new NIVision.Range(0, 255);
    public final NIVision.Range sRange = new NIVision.Range(0, 255);
    public final NIVision.Range vRange = new NIVision.Range(0, 255);

    public final NIVision.ParticleFilterOptions2 options = new NIVision.ParticleFilterOptions2(0, 0, 1, 1);
    public final NIVision.ParticleFilterCriteria2[] criteria = {
        new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MIN, 100, 0, 0)};

    public Cameras(USBCamera front, USBCamera rear) {
        this.front = front;
        this.rear = rear;
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

    public double getAngleUp() {
        Image image = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        getImage(image);
        Image binary = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_U8, 0);
        NIVision.imaqColorThreshold(binary, image, 255, NIVision.ColorMode.HSV, hRange, sRange, vRange);
        NIVision.imaqParticleFilter4(binary, binary, criteria, options, null);
        int count = NIVision.imaqCountParticles(binary, 1);
        double maxArea = 0;
        int maxIndex = 0;
        for (int index = 0; index < count; index++) {
            double area = NIVision.imaqMeasureParticle(image, index, 0, NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
            maxIndex = area > maxArea ? index : maxIndex;
        }
        if (count != 0) {
            double height = NIVision.imaqMeasureParticle(image, maxIndex, 0, NIVision.MeasurementType.MT_FIRST_PIXEL_Y);
            return CAMERA_ANGLE + Y_ANGLE_PER_PIXEL * height;
        }
        return 50;//TODO: find what to return here
    }

    public double getDistance() {
        double angleUp = getAngleUp();
        return TARGET_HEIGHT / Math.tan(Math.toRadians(angleUp + CAMERA_ANGLE));
    }

    @Override
    protected void initDefaultCommand() {
    }

}
