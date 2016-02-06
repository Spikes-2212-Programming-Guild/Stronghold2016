package com.spikes2212.robot2016;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public interface PWM {
		public static final int LEFT_FRONT_VICTOR = 3;
		public static final int LEFT_REAR_VICTOR = 2;
		public static final int RIGHT_FRONT_VICTOR = 1;
		public static final int RIGHT_REAR_VICTOR = 0;
		public static final int TRIZ_MOTOR = 0;
		public static final int FOLDER_MOTOR = 8;
		public static final int PICKER_MOTOR = 9;
		public static final int SHOOTER_MOTOR = 1;
	}

	public interface DIO {
		public static final int LEFT_ENCODER_A = 10;
		public static final int LEFT_ENCODER_B = 11;
		public static final int RIGHT_ENCODER_A = 12;
		public static final int RIGHT_ENCODER_B = 13;
		public static final int TRIZ_ENCODER_A = 14;
		public static final int TRIZ_ENCODER_B = 15;
		public static final int FOLDER_ENCODER_A = 16;
		public static final int FOLDER_ENCODER_B = 17;
		public static final int TRIZ_UP = 1;
		public static final int TRIZ_DOWN = 2;
		public static final int TRIZ_UNDER_PORTCULLIS = 3;
		public static final int FOLDER_UP = 4;
		public static final int FOLDER_DOWN = 5;
		public static final int BALL_INSIDE = 6;
	}

	public interface AnalogInput {
	}
}
