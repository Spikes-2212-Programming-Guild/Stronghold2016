package com.spikes2212.robot2016;

import com.spikes2212.robot2016.RobotMap.CAN;
import com.spikes2212.robot2016.RobotMap.DIO;
import com.spikes2212.robot2016.RobotMap.PWM;
import com.spikes2212.robot2016.RobotMap.USB;
import com.spikes2212.robot2016.commands.autonomous.CrossLowBar;
import com.spikes2212.robot2016.commands.camera.VisionRunnable;
import com.spikes2212.robot2016.subsystems.Drivetrain;
import com.spikes2212.robot2016.subsystems.Folder;
import com.spikes2212.robot2016.subsystems.Picker;
import com.spikes2212.robot2016.subsystems.Shooter;
import com.spikes2212.robot2016.subsystems.Triz;
import com.spikes2212.robot2016.subsystems.Vision;
import com.spikes2212.robot2016.util.Gearbox;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Gearbox right;
	public static Gearbox left;
	public static Drivetrain drivetrain;
	public static Triz triz;
	public static Folder folder;
	public static Picker picker;
	public static Shooter shooter;
	public static Vision vision;

	private Command autoCommand;

	Thread visionThread;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		left = new Gearbox(PWM.LEFT_FRONT_MOTOR, PWM.LEFT_REAR_MOTOR, DIO.LEFT_ENCODER_A, DIO.LEFT_ENCODER_B);
		right = new Gearbox(PWM.RIGHT_FRONT_MOTOR, PWM.RIGHT_REAR_MOTOR, DIO.RIGHT_ENCODER_A, DIO.RIGHT_ENCODER_B);
		drivetrain = new Drivetrain(left, right);
		triz = new Triz(PWM.TRIZ_MOTOR, DIO.TRIZ_UP, DIO.TRIZ_DOWN, DIO.TRIZ_UNDER_PORTCULLIS, DIO.TRIZ_ENCODER_A,
				DIO.TRIZ_ENCODER_B);
		shooter = new Shooter(CAN.SHOOTER_MOTOR);
		picker = new Picker(PWM.PICKER_MOTOR, DIO.BALL_INSIDE);
		folder = new Folder(PWM.FOLDER_MOTOR, DIO.FOLDER_UP, DIO.FOLDER_DOWN, DIO.FOLDER_ENCODER_A,
				DIO.FOLDER_ENCODER_B);
		vision = new Vision(USB.FRONT_CAMERA, USB.REAR_CAMERA);
		visionThread = new Thread(new VisionRunnable());
		visionThread.start();
		oi = new OI();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		writeData();
	}

	@Override
	public void autonomousInit() {
		try {
			autoCommand = new CrossLowBar();
			autoCommand.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		writeData();
	}

	@Override
	public void teleopInit() {
		drivetrain.setMaximumSpeed(Constants.VERY_HIGH_MAX_SPEED);
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		writeData();

	}

	public static void writeData() {
		try {
			SmartDashboard.putBoolean("DB/LED 0", picker.isBoulderInside());
			SmartDashboard.putString("DB/String 0", "folder up: " + folder.isUp());
			SmartDashboard.putString("DB/String 1", "folder down: " + folder.isDown());
			SmartDashboard.putString("DB/String 2", "triz up: " + triz.isUp());
			SmartDashboard.putString("DB/String 3", "triz down: " + triz.isDown());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

}
