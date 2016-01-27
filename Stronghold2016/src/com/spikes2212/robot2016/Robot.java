package com.spikes2212.robot2016;

import java.util.Optional;

import com.spikes2212.robot2016.commands.advanced.Cross.Defense;
import com.spikes2212.robot2016.commands.advanced.CrossAndDropAndReturn;
import com.spikes2212.robot2016.commands.advanced.CrossAndReturn;
import com.spikes2212.robot2016.subsystems.Drivetrain;
import com.spikes2212.robot2016.subsystems.Triz;
import com.spikes2212.robot2016.subsystems.roller.Folder;
import com.spikes2212.robot2016.subsystems.roller.Picker;
import com.spikes2212.robot2016.subsystems.roller.Shooter;
import com.spikes2212.robot2016.util.Gearbox;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

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
	public static Gyro gyro;

	SendableChooser defenseChooser;
	SendableChooser autoChooser;
	Optional<Command> autoCommand = Optional.empty();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		gyro = new ADXRS450_Gyro();
		left = new Gearbox(RobotMap.LEFT_FRONT_VICTOR_PORT, RobotMap.LEFT_REAR_VICTOR_PORT,
				RobotMap.LEFT_ENCODER_CHANNEL_A, RobotMap.LEFT_ENCODER_CHANNEL_B);
		right = new Gearbox(RobotMap.RIGHT_FRONT_VICTOR_PORT, RobotMap.RIGHT_REAR_VICTOR_PORT,
				RobotMap.RIGHT_ENCODER_CHANNEL_A, RobotMap.RIGHT_ENCODER_CHANNEL_B);
		drivetrain = new Drivetrain(gyro, left, right);
		triz = new Triz(RobotMap.TRIZ_TALON_PORT, RobotMap.TRIZ_LIMITSWICH_UP_PORT, RobotMap.TRIZ_LIMITSWICH_DOWN_PORT,
				RobotMap.TRIZ_UNDER_PORTCULLIS_PORT, RobotMap.TRIZ_ENCODER_CHANNEL_A, RobotMap.TRIZ_ENCODER_CHANNEL_B);
		shooter = new Shooter(RobotMap.SHOOTER_TALON_PORT);
		picker = new Picker(RobotMap.PICKER_TALON_PORT, RobotMap.BALL_LIMIT_SWITCH_CHANNEL);
		folder = new Folder(RobotMap.FOLDER_TALON_PORT);
		defenseChooser = new SendableChooser();
		defenseChooser.addDefault("Low Bar", Defense.LOW_BAR);
		defenseChooser.addObject("Portcullis", Defense.PORTCULLIS);
		defenseChooser.addObject("Cheval de Frise", Defense.CHEVAL_DE_FRISE);
		defenseChooser.addObject("Moat", Defense.MOAT);
		defenseChooser.addObject("Ramparts", Defense.RAMPARTS);
		defenseChooser.addObject("Rough Terrain", Defense.ROUGH_TERRAIN);
		defenseChooser.addObject("Rock Wall", Defense.ROCK_WALL);
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Cross & Return", "CrossAndReturn");
		autoChooser.addObject("Cross, Drop & Return", "CrossAndDropAndReturn");
		autoChooser.addObject("Cross & Shoot", "CrossAndShoot");
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
		try {
			Defense defense = (Defense) defenseChooser.getSelected();
			switch ((String) autoChooser.getSelected()) {
			case "CrossAndReturn":
				autoCommand = Optional.of(new CrossAndReturn(defense));
				break;
			case "CrossAndDropAndReturn":
				autoCommand = Optional.of(new CrossAndDropAndReturn(defense));
				break;
			case "CrossAndShoot":
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		autoCommand.ifPresent(Command::start);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
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
