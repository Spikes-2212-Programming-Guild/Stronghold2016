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

	public static final double METER = 1;
	public static final double FEET = 0.3048;
	public static final double INCH = FEET / 12;

	public static final double TRIZ_UP_SPEED = 0.4;
	public static final double TRIZ_DOWN_SPEED = 0.3;
	public static final double FOLDER_UP_SPEED = 0.45;
	public static final double FOLDER_DOWN_SPEED = 0.45;
	public static final double PICKER_ROLL_IN_SPEED = 0.2;
	public static final double PICKER_ROLL_OUT_SPEED = 1;

	public static final double TRIZ_CONTRACTED_ANGLE = 0;
	public static final double TRIZ_EXPANDED_ANGLE = 0;
	public static final double FOLDER_DOWN_ANGLE = 0;
	public static final double FOLDER_UP_ANGLE = 0;

	public static final double TRIZ_ANGLE_PER_PULSE = 1;
	public static final double FOLDER_ANGLE_PER_PULSE = 1;

	public static final double DRIVETRAIN_WHEEL_PERIMETER = 0.203 * Math.PI * METER;
	public static final double LEFT_DISTANCE_PER_PULSE = DRIVETRAIN_WHEEL_PERIMETER / 360;
	public static final double RIGHT_DISTANCE_PER_PULSE = DRIVETRAIN_WHEEL_PERIMETER / 360;
	public static final double FREE_FALL_GRAVITY = 1.024;
	public static final double MAX_LEFT_VELOCITY = 4;
	public static final double MAX_RIGHT_VELOCITY = 4;
	public static final double HIGH_MAX_SPEED = 0.8;
	public static final double LOW_MAX_SPEED = 0.4;

	public static final double SHOOTING_HIGH_VOLTAGE = 0.98 * 12;
	public static final double SHOOTING_LOW_VOLTAGE = 0.3 * 12;

	public interface Vision {
		public static final Range rRange = new Range(0, 255);
		public static final Range gRange = new Range(250, 255);
		public static final Range bRange = new Range(250, 255);

		public static final ParticleFilterOptions2 options = new NIVision.ParticleFilterOptions2(0, 0, 1, 1);
		public static final double MIN_WIDTH_PIXELS = 40;
		public static final ParticleFilterCriteria2[] criteria = { new NIVision.ParticleFilterCriteria2(
				NIVision.MeasurementType.MT_BOUNDING_RECT_WIDTH, MIN_WIDTH_PIXELS, 100, 0, 0) };
		public static final double REFLECTIVE_WIDTH = 0.73;
		public static final int RESOLUTION_WIDTH = 320;
		public static final double TARGET_HEIGHT = 0.68;
		public static final double VIEW_HORIZONTAL_ANGLE = 53;
	}
}
