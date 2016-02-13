package com.spikes2212.robot2016;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.ParticleFilterCriteria2;
import com.ni.vision.NIVision.ParticleFilterOptions2;
import com.ni.vision.NIVision.Range;

public class Constants {

	// Limit Switches are normally open
	// Conventions about units:
	/*
	 * Time - second Distance - centimeter
	 * 
	 * Velocity - centimeter / second
	 * 
	 * "Speed" - pulse width
	 * 
	 * Acceleration - centimeter / second^2
	 * 
	 * Angle - degree
	 * 
	 * Angular Rate - degree / second
	 */

	public static final double TRIZ_SPEED = 0.5;
	public static final double FOLDER_UP_SPEED = 0.5;
	public static final double FOLDER_DOWN_SPEED = 0.5;
	public static final double PICKER_ROLL_IN_SPEED = 0.8;
	public static final double PICKER_ROLL_OUT_SPEED = 0.8;
	public static final double SHOOTING_VOLTAGE = 5;

	public static final double DRIVE_TO_PORTCULLIS_DISTANCE = 0;

	public static final double LIFTING_PORTCULLIS_DISTANCE = 0;

	public static final double LOW_BAR_DISTANCE = 0;
	public static final double AFTER_LIFTING_PORTCULLIS_DISTANCE = 0;
	public static final double MOAT_DISTANCE = 0;

	public static final double TRIZ_UP_ANGLE = 0;
	public static final double TRIZ_DOWN_POSITION = 0;
	public static final double FOLDER_UP_ANGLE = 0;
	public static final double FOLDER_DOWN_ANGLE = 0;

	public static final double FREE_FALL_GRAVITY = 9.8;

	public static final double LEFT_DISTANCE_PER_PULSE = 0;
	public static final double RIGHT_DISTANCE_PER_PULSE = 0;
	public static final double TRIZ_ANGLE_PER_PULSE = 0;
	public static final double FOLDER_ANGLE_PER_PULSE = 0;

	public interface Vision {
		public static final Range rRange = new Range(254, 255);
		public static final Range gRange = new Range(254, 255);
		public static final Range bRange = new Range(254, 255);

		public static final ParticleFilterOptions2 options = new NIVision.ParticleFilterOptions2(0, 0, 1, 1);
		public static final double AREA_MIN = 50;
		public static final ParticleFilterCriteria2[] criteria = { new NIVision.ParticleFilterCriteria2(
				NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MIN, 100, 0, 0) };
		public static final double REFLECTIVE_WIDTH = 1;
		public static final int RESOLUTION_WIDTH = 640;
		public static final double VIEW_HORIZONTAL_ANGLE = 51;
	}
}
