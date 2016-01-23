package com.spikes2212.robot2016;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
/**
 * @author Itamar
 */
public class RobotMap {
	public static final int RIGHT_FRONT_VICTOR_PORT = 0;
	public static final int RIGHT_REAR_VICTOR_PORT = 0;
	public static final int LEFT_FRONT_VICTOR_PORT = 0;
	public static final int LEFT_REAR_VICTOR_PORT = 0;
	public static final int RIGHT_DRIVER_JOYSTICK_PORT = 0;
	public static final int LEFT_DRIVER_JOYSTICK_PORT = 0;
	public static final int NAVIGATOR_JOYSTICK_PORT = 0;

	class DigitalInputMap {

		public static final int ROLLER_FOLDER_LIMIT_EXTEND_PORT = 0;
		public static final int ROLLER_FOLDER_LIMIT_RETRACT_PORT = 0;
		public static final int BOULDER_LIMIT_PORT = 0;
		public static final int TRIZ_FOLD_LIMIT_PORt = 0;
		public static final int TRIZ_UNFOLD_LIMIT_PORT = 0;
	}

	class RollerMap {
		public static final int ROLLER_FOLDER_TALON_PORT = 0;
		public static final int ROLLER_TALON_PORT = 0;
	}

	class TrizMap {
		public static final int TALON_TRIZ_FOLDER_PORT = 0;
	}

	class AnalogInputMap {
		public static final int ROLLER_FOLDER_DISTANCE_SENSOR_PORT = 0;
		public static final int TRIZ_POTENTIOMETER_PORT = 0;
	}
}
