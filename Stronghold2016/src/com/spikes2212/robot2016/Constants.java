package com.spikes2212.robot2016;

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
}
