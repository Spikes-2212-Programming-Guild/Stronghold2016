package com.spikes2212.robot2016;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public interface USB {
		public static final String FRONT_CAMERA = "cam0";
		public static final String REAR_CAMERA = "cam1";
	}

	public interface PWM {
		public static final int LEFT_FRONT_VICTOR = 0;
		public static final int LEFT_REAR_VICTOR = 0;
		public static final int RIGHT_FRONT_VICTOR = 0;
		public static final int RIGHT_REAR_VICTOR = 0;
		public static final int TRIZ_MOTOR = 0;
		public static final int FOLDER_MOTOR = 0;
		public static final int PICKER_MOTOR = 0;
		public static final int SHOOTER_MOTOR = 0;
	}

	public interface DIO {
		public static final int LEFT_ENCODER_A = 1;
		public static final int LEFT_ENCODER_B = 2;
		public static final int RIGHT_ENCODER_A = 3;
		public static final int RIGHT_ENCODER_B = 4;
		public static final int TRIZ_ENCODER_A = 5;
		public static final int TRIZ_ENCODER_B = 6;
		public static final int FOLDER_ENCODER_A = 7;
		public static final int FOLDER_ENCODER_B = 8;
		public static final int TRIZ_UP = 9;
		public static final int TRIZ_DOWN = 10;
		public static final int TRIZ_UNDER_PORTCULLIS = 11;
		public static final int FOLDER_UP = 12;
		public static final int FOLDER_DOWN = 13;
		public static final int BALL_INSIDE = 14;
	}

	public interface AnalogInput {
	}
}
