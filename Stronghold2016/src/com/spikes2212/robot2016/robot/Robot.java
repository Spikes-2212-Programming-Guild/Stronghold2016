package com.spikes2212.robot2016.robot;

import com.spikes2212.robot2016.subsystems.Drivetrain;
import com.spikes2212.robot2016.util.Gearbox;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static Drivetrain drivetrain;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		Encoder leftFront = new Encoder(RobotMap.LEFT_FRONT_ENCODER_PORT1, RobotMap.LEFT_FRONT_ENCODER_PORT2);
		Encoder leftRear = new Encoder(RobotMap.LEFT_REAR_ENCODER_PORT1, RobotMap.LEFT_REAR_ENCODER_PORT2);
		Encoder rightFront = new Encoder(RobotMap.RIGHT_FRONT_ENCODER_PORT1, RobotMap.RIGHT_FRONT_ENCODER_PORT2);
		Encoder rightRear = new Encoder(RobotMap.RIGHT_REAR_ENCODER_PORT1, RobotMap.RIGHT_REAR_ENCODER_PORT2);
		Gearbox left = new Gearbox(RobotMap.LEFT_REAR_MOTOR_PORT, RobotMap.LEFT_FRONT_MOTOR_PORT, leftFront, leftRear);
		Gearbox right = new Gearbox(RobotMap.RIGHT_REAR_MOTOR_PORT, RobotMap.RIGHT_FRONT_MOTOR_PORT, rightFront,
				rightRear);
		drivetrain = new Drivetrain(left, right, new Gyro(RobotMap.GYRO_PORT), new BuiltInAccelerometer());
	}

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
