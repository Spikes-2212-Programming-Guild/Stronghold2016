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

	@Calibrate(desc = "Choose a fitting value", unit = "pwm", min = 0, max = 1)
	public static final double TRIZ_SPEED = 0;
	@Calibrate(desc = "Choose a fitting value", unit = "pwm", min = 0, max = 1)
	public static final double FOLDER_UP_SPEED = 0;
	@Calibrate(desc = "Choose a fitting value", unit = "pwm", min = 0, max = 1)
	public static final double FOLDER_DOWN_SPEED = 0;
	@Calibrate(desc = "Choose a fitting value", unit = "pwm", min = 0, max = 1)
	public static final double PICKER_ROLL_IN_SPEED = 0;
	@Calibrate(desc = "Choose a fitting value", unit = "pwm", min = 0, max = 1)
	public static final double PICKER_ROLL_OUT_SPEED = 0;
	@Calibrate(desc = "Choose a fitting value", unit = "volt", max = 12)
	public static final double SHOOTING_VOLTAGE = 0;

	@Calibrate(desc = "Choose a fitting value", unit = "pwm", min = 0, max = 1)
	public static final double DRIVE_TO_PORTCULLIS_DISTANCE = 0;

	@Calibrate(desc = "Check with the manual/robot", unit = "meter")
	public static final double LIFTING_PORTCULLIS_DISTANCE = 0;
	@Calibrate(desc = "Check with the manual/robot", unit = "meter")
	public static final double LOW_BAR_DISTANCE = 0;
	@Calibrate(desc = "Check with the manual/robot", unit = "meter")
	public static final double AFTER_LIFTING_PORTCULLIS_DISTANCE = 0;

	@Calibrate(desc = "Set AFTER calibrating the encoders", unit = "degree")
	public static final double TRIZ_UP_ANGLE = 0;
	@Calibrate(desc = "Set AFTER calibrating the encoders", unit = "degree")
	public static final double TRIZ_DOWN_ANGLE = 0;
	@Calibrate(desc = "Set AFTER calibrating the encoders", unit = "degree")
	public static final double FOLDER_UP_ANGLE = 0;
	@Calibrate(desc = "Set AFTER calibrating the encoders", unit = "degree")
	public static final double FOLDER_DOWN_ANGLE = 0;

	@Calibrate(desc = "A normalized value of g, should be around 1", unit = "none", min = 0.9, max = 1.1)
	public static final double FREE_FALL_GRAVITY = 0;

	@Calibrate(desc = "Run the robot a known distance and see the encoders' count", unit = "meter/pulse")
	public static final double LEFT_DISTANCE_PER_PULSE = 0;
	@Calibrate(desc = "Run the robot a known distance and see the encoders' count", unit = "meter/pulse")
	public static final double RIGHT_DISTANCE_PER_PULSE = 0;
	@Calibrate(desc = "Move the triz a known angle and see the encoders count", unit = "meter/pulse")
	public static final double TRIZ_ANGLE_PER_PULSE = 0;
	@Calibrate(desc = "Run the robot a known angle and see the encoders count", unit = "meter/pulse")
	public static final double FOLDER_ANGLE_PER_PULSE = 0;
}
