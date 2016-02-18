package com.spikes2212.robot2016;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.spikes2212.robot2016.Field.Defense;
import com.spikes2212.robot2016.Field.DefenseLocation;
import com.spikes2212.robot2016.Field.Direction;
import com.spikes2212.robot2016.RobotMap.CAN;
import com.spikes2212.robot2016.RobotMap.DIO;
import com.spikes2212.robot2016.RobotMap.PWM;
import com.spikes2212.robot2016.RobotMap.USB;
import com.spikes2212.robot2016.commands.RetractAll;
import com.spikes2212.robot2016.commands.autonomous.Cross;
import com.spikes2212.robot2016.commands.autonomous.CrossAndDropAndReturn;
import com.spikes2212.robot2016.commands.autonomous.CrossAndReturn;
import com.spikes2212.robot2016.commands.drivetrain.PIDTurnDriveByAngleush;
import com.spikes2212.robot2016.subsystems.Cameras;
import com.spikes2212.robot2016.subsystems.Drivetrain;
import com.spikes2212.robot2016.subsystems.Folder;
import com.spikes2212.robot2016.subsystems.Picker;
import com.spikes2212.robot2016.subsystems.Shooter;
import com.spikes2212.robot2016.subsystems.Triz;
import com.spikes2212.robot2016.util.Gearbox;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
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
	public static Gyro gyro;
	public static Accelerometer accelerometer;
	public static Cameras cameras;

	public static Command autoCommand;

	SendableChooser defenseChooser;
	SendableChooser locationChooser;
	SendableChooser autoChooser;

	Image image, binary;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		gyro = new ADXRS450_Gyro();
		accelerometer = new BuiltInAccelerometer();
		left = new Gearbox(PWM.LEFT_FRONT_MOTOR, PWM.LEFT_REAR_MOTOR, DIO.LEFT_ENCODER_A, DIO.LEFT_ENCODER_B);
		right = new Gearbox(PWM.RIGHT_FRONT_MOTOR, PWM.RIGHT_REAR_MOTOR, DIO.RIGHT_ENCODER_A, DIO.RIGHT_ENCODER_B);
		drivetrain = new Drivetrain(left, right, gyro, accelerometer);
		triz = new Triz(PWM.TRIZ_MOTOR, DIO.TRIZ_UP, DIO.TRIZ_DOWN, DIO.TRIZ_UNDER_PORTCULLIS, DIO.TRIZ_ENCODER_A,
				DIO.TRIZ_ENCODER_B);
		shooter = new Shooter(CAN.SHOOTER_MOTOR);
		picker = new Picker(PWM.PICKER_MOTOR, DIO.BALL_INSIDE);
		folder = new Folder(PWM.FOLDER_MOTOR, DIO.FOLDER_UP, DIO.FOLDER_DOWN, DIO.FOLDER_ENCODER_A,
				DIO.FOLDER_ENCODER_B);
		cameras = new Cameras(USB.FRONT_CAMERA, USB.REAR_CAMERA);
		oi = new OI();
		defenseChooser = new SendableChooser();
		defenseChooser.addDefault("Low Bar", Defense.LOW_BAR);
		defenseChooser.addObject("Portcullis", Defense.PORTCULLIS);
		defenseChooser.addObject("Cheval de Frise", Defense.CHEVAL_DE_FRISE);
		defenseChooser.addObject("Moat", Defense.MOAT);
		defenseChooser.addObject("Ramparts", Defense.RAMPARTS);
		defenseChooser.addObject("Rough Terrain", Defense.ROUGH_TERRAIN);
		defenseChooser.addObject("Rock Wall", Defense.ROCK_WALL);
		locationChooser = new SendableChooser();
		locationChooser.addDefault("1", DefenseLocation.k1);
		locationChooser.addObject("2", DefenseLocation.k2);
		locationChooser.addObject("3", DefenseLocation.k3);
		locationChooser.addObject("4", DefenseLocation.k4);
		locationChooser.addObject("5", DefenseLocation.k5);
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Cross", "Cross");
		autoChooser.addObject("Cross & Return", "CrossAndReturn");
		autoChooser.addObject("Cross, drop & Return", "CrossAndDropAndReturn");
		autoChooser.addObject("Cross & score low", "CrossAndScoreLow");
		autoChooser.addObject("Cross & score high", "CrossAndScoreHigh");
		SmartDashboard.putData("Defense", defenseChooser);
		SmartDashboard.putData("Location", locationChooser);
		SmartDashboard.putData("Auto", autoChooser);
		image = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		binary = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
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
	}

	@Override
	public void teleopInit() {
		try {
			Defense defense = (Defense) defenseChooser.getSelected();
			DefenseLocation location = (DefenseLocation) locationChooser.getSelected();
			switch ((String) autoChooser.getSelected()) {
			case "Cross":
				autoCommand = new Cross(defense, Direction.FORWARD);
				break;
			case "CrossAndReturn":
				autoCommand = new CrossAndReturn(defense);
				break;
			case "CrossAndDropAndReturn":
				autoCommand = new CrossAndDropAndReturn(defense);
				break;
			// case "CrossAndScoreLow":
			// autoCommand = new CrossAndScoreGoal(defense, location, Goal.LOW);
			// break;
			// case "CrossAndScoreHigh":
			// autoCommand = new CrossAndScoreGoal(defense, location,
			// Goal.HIGH);
			// break;
			default:
				autoCommand = new CommandGroup();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		SmartDashboard.putData(autoCommand);
		SmartDashboard.putData(new PIDTurnDriveByAngleush(0));
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		writeSensorData();
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
		SmartDashboard.putNumber("left distance", drivetrain.getLeftDistance());
		SmartDashboard.putNumber("right distance", drivetrain.getRightDistance());
		SmartDashboard.putBoolean("triz up", triz.isUp());
		SmartDashboard.putBoolean("triz down", triz.isDown());
		SmartDashboard.putNumber("triz distance", triz.getAngle());
		SmartDashboard.putBoolean("folder up", folder.isUp());
		SmartDashboard.putBoolean("folder down", folder.isDown());
		SmartDashboard.putNumber("folder distance", folder.getAngle());
		SmartDashboard.putBoolean("boulder inside", picker.isBoulderInside());
		SmartDashboard.putNumber("yaw angle", drivetrain.getYawAngle());
		SmartDashboard.putNumber("angle with floor", drivetrain.getAngleWithFloor());
	}

}
