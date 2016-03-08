package com.spikes2212.robot2016;

import com.spikes2212.robot2016.RobotMap.CAN;
import com.spikes2212.robot2016.RobotMap.DIO;
import com.spikes2212.robot2016.RobotMap.PWM;
import com.spikes2212.robot2016.RobotMap.USB;
import com.spikes2212.robot2016.commands.RetractAll;
import com.spikes2212.robot2016.commands.autonomous.CrossChevalDeFrise;
import com.spikes2212.robot2016.commands.autonomous.CrossLowBar;
import com.spikes2212.robot2016.commands.autonomous.CrossLowBarAndReturn;
import com.spikes2212.robot2016.commands.autonomous.CrossPortcullis;
import com.spikes2212.robot2016.commands.autonomous.CrossRoughTerrain;
import com.spikes2212.robot2016.commands.autonomous.Reach;
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
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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

	public static Command autoCommand;

	Thread visionThread;

	SendableChooser autoChooser;

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
		autoChooser = new SendableChooser();
		autoChooser.addDefault("No autonomous", new CommandGroup());
		autoChooser.addObject("Reach", new Reach());
		autoChooser.addObject("Low Bar", new CrossLowBar());
		autoChooser.addObject("Low Bar + Return", new CrossLowBarAndReturn());
		autoChooser.addObject("Cheval de Frise", new CrossChevalDeFrise());
		autoChooser.addObject("Rough Terrain", new CrossRoughTerrain());
		autoChooser.addObject("Portcullis", new CrossPortcullis());
		SmartDashboard.putData("Auto", autoChooser);
		SmartDashboard.putData(new RetractAll());
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
		writeSensorData();
	}

	@Override
	public void autonomousInit() {
		try {
			autoCommand = (Command) autoChooser.getSelected();
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
		writeSensorData();
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
		writeSensorData();

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	private void writeSensorData() {
		try {
			SmartDashboard.putNumber("left distance", drivetrain.getLeftDistance());
			SmartDashboard.putNumber("right distance", drivetrain.getRightDistance());
			SmartDashboard.putBoolean("triz up", triz.isUp());
			SmartDashboard.putBoolean("triz down", triz.isDown());
			SmartDashboard.putBoolean("folder up", folder.isUp());
			SmartDashboard.putBoolean("folder down", folder.isDown());
			SmartDashboard.putBoolean("boulder inside", picker.isBoulderInside());
			SmartDashboard.putString("max speed", drivetrain.getMaximumSpeed() * 100 + "%");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
