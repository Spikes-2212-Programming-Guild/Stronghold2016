
package com.spikes2212.robot2016;

import com.spikes2212.robot2016.subsystems.Drivetrain;
import com.spikes2212.robot2016.subsystems.Folder;
import com.spikes2212.robot2016.subsystems.Roller;
import com.spikes2212.robot2016.subsystems.Triz;
import com.spikes2212.robot2016.util.Gearbox;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
/**
 * 
 * @author Itamar
 *
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Gearbox right = new Gearbox(RobotMap.RIGHT_FRONT_VICTOR_PORT, RobotMap.RIGHT_REAR_VICTOR_PORT);
	public static Gearbox left = new Gearbox(RobotMap.LEFT_FRONT_VICTOR_PORT, RobotMap.LEFT_REAR_VICTOR_PORT);
	public static Drivetrain drivetrain = new Drivetrain(left, right);
	public static Folder folder = new Folder(RobotMap.RollerMap.ROLLER_FOLDER_TALON_PORT,
			RobotMap.DigitalInputMap.ROLLER_FOLDER_LIMIT_EXTEND_PORT,
			RobotMap.DigitalInputMap.ROLLER_FOLDER_LIMIT_RETRACT_PORT);
	public static Roller roller = new Roller(RobotMap.RollerMap.ROLLER_TALON_PORT,
			RobotMap.AnalogInputMap.ROLLER_FOLDER_DISTANCE_SENSOR_PORT, RobotMap.DigitalInputMap.BOULDER_LIMIT_PORT);
	public static Triz triz = new Triz(RobotMap.TrizMap.TALON_TRIZ_FOLDER_PORT,
			RobotMap.DigitalInputMap.TRIZ_FOLD_LIMIT_PORt, RobotMap.DigitalInputMap.TRIZ_UNFOLD_LIMIT_PORT,
			RobotMap.AnalogInputMap.TRIZ_POTENTIOMETER_PORT);

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
