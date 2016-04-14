package com.spikes2212.robot2016;

public class Constants {

	// Limit Switches are normally open
	// Conventions about units:
	/*
	 * Time - second
	 * 
	 * Distance - meter
	 * 
	 * Velocity - meter / second
	 * 
	 * "Speed" - pulse width
	 * 
	 * Acceleration - meter / second^2
	 * 
	 * Angle - degree
	 * 
	 * Angular Rate - degree / second
	 */

	public static final double METER = 1;
	public static final double FEET = 0.3048;
	public static final double INCH = FEET / 12;

	public static final double TRIZ_UP_SPEED = 0.7;
	// 0.4
	public static final double TRIZ_DOWN_SPEED = 0.5;
	// 0.3
	public static final double FOLDER_UP_SPEED = 0.45;
	public static final double FOLDER_DOWN_SPEED = 0.45;
	public static final double PICKER_ROLL_IN_SPEED = 0.8;
	public static final double PICKER_ROLL_OUT_SPEED = 1;

	public static final double TRIZ_CONTRACTED_ANGLE = 0;
	public static final double TRIZ_EXPANDED_ANGLE = 0;
	public static final double FOLDER_DOWN_ANGLE = 0;
	public static final double FOLDER_UP_ANGLE = 0;

	public static final double TRIZ_ANGLE_PER_PULSE = 360.0 / 1024;
	public static final double FOLDER_ANGLE_PER_PULSE = 360.0 / 1024;

	public static final double DRIVETRAIN_WHEEL_PERIMETER = 0.203 * Math.PI * METER;
	public static final double LEFT_DISTANCE_PER_PULSE = DRIVETRAIN_WHEEL_PERIMETER / 360;
	public static final double RIGHT_DISTANCE_PER_PULSE = DRIVETRAIN_WHEEL_PERIMETER / 360;
	public static final double FREE_FALL_GRAVITY = 1.024;
	public static final double MAX_LEFT_VELOCITY = 4;
	public static final double MAX_RIGHT_VELOCITY = 4;
	
	public static final double VERY_HIGH_MAX_SPEED = 1.0;
	public static final double HIGH_MAX_SPEED = 0.6;
	public static final double CROSS_LOW_BAR_MAX_SPEED = 0.4;
	public static final double MEDIUM_MAX_SPEED = 0.45;
	public static final double LOW_MAX_SPEED = 0.35;

	public static final double SHOOTING_HIGH_VOLTAGE = 11;
	public static final double SHOOTER_ACCELERATION_TIME = 5;
	public static final double TIME_GAP_FOR_SHOOTING = 2;
	public static final double ROLL_IN_TIME = 2;

//	public static final int EXPOSURE_FRONT = 0;
//	public static final int EXPOSURE_REAR = 2;
	public static final int FPS = 30;
	public static final int RESOLUTION_WIDTH = 320;
	public static final int RESOLUTION_HEIGHT = 240;
}
