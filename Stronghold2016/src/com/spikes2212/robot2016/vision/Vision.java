package com.spikes2212.robot2016.vision;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.ColorMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.NIVision.MeasurementType;
import com.ni.vision.NIVision.ParticleFilterCriteria2;
import com.ni.vision.NIVision.Range;
import com.spikes2212.robot2016.vision.Reflective.Orientation;

public class Vision {

	public static final double VIEW_ANGLE = 59;

	public static final double AREA_MIN = 60;
	public static Range hRange = new Range(0, 255);
	public static Range sRange = new Range(0, 255);
	public static Range vRange = new Range(0, 255);

	public static ParticleFilterCriteria2[] criteria = {
			new ParticleFilterCriteria2(MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MIN, 100, 0, 0) };
	public static NIVision.ParticleFilterOptions2 options = new NIVision.ParticleFilterOptions2(0, 0, 1, 1);

	public static Optional<Double> getDistanceFrom(Image image) {
		List<Reflective> reflectives = getReflectives(image);
		return reflectives.isEmpty() ? Optional.empty() : Optional.of(reflectives.get(0).getWidth());
	}

	public static List<Reflective> getReflectives(Image image) {
		Image binary = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		NIVision.imaqColorThreshold(binary, image, 255, ColorMode.HSV, hRange, sRange, vRange);
		NIVision.imaqParticleFilter4(binary, binary, criteria, options, null);
		int count = NIVision.imaqCountParticles(binary, 1);
		List<Reflective> reflectives = new ArrayList<>();
		for (int index = 0; index < count; index++) {
			double area = NIVision.imaqMeasureParticle(image, index, 0, MeasurementType.MT_AREA_BY_IMAGE_AREA);
			double left = NIVision.imaqMeasureParticle(image, index, 0, MeasurementType.MT_BOUNDING_RECT_LEFT);
			double right = NIVision.imaqMeasureParticle(image, index, 0, MeasurementType.MT_BOUNDING_RECT_RIGHT);
			double top = NIVision.imaqMeasureParticle(image, index, 0, MeasurementType.MT_BOUNDING_RECT_TOP);
			double bottom = NIVision.imaqMeasureParticle(image, index, 0, MeasurementType.MT_BOUNDING_RECT_BOTTOM);
			Orientation orientation;
			if (right - left > bottom - top) {
				orientation = Orientation.HORIZONTAL;
			} else {
				orientation = Orientation.VERTICAL;
			}
			if (orientation == Orientation.HORIZONTAL) {
				reflectives.add(new Reflective(orientation, left, right, top, bottom, area));
			}
		}
		reflectives.sort((a, b) -> (int) (-a.getArea() + b.getArea()));
		return reflectives;
	}
}
