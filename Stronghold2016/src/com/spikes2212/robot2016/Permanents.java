package com.spikes2212.robot2016;

public class Permanents {

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
	 * 
	 */

	public static final int TZOORS_CONSTANT = 4 - 1;

	public static final int TRIZ_SPEED = 0;
	public static final int FOLDER_SPEED = 0;
	public static final int PICKER_ROLL_IN_SPEED = 0;
	public static final int PICKER_ROLL_OUT_SPEED = 0;
	public static final int SHOOT_SPEED = 0;

	public static final double DRIVE_TO_PORTCULLIS_VELOCITY = 0;

	public static final double LIFTING_PORTCULLIS_DISTANCE = 0;

	public static final double LOW_BAR_DISTANCE = 0;
	public static final double AFTER_LIFTING_PORTCULLIS_DISTANCE = 0;
	public static final double MOAT_DISTANCE = 0;

	public static final double LIFTER_UP_DISTANCE = 0;
	public static final double LIFTER_DOWN_DISTANCE = 0;

}
