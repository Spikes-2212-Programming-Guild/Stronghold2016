package com.spikes2212.robot2016;

import static com.spikes2212.robot2016.Constants.AFTER_LIFTING_PORTCULLIS_DISTANCE;
import static com.spikes2212.robot2016.Constants.DRIVE_TO_PORTCULLIS_DISTANCE;
import static com.spikes2212.robot2016.Constants.FOLDER_DISTANCE_PER_PULSE;
import static com.spikes2212.robot2016.Constants.FOLDER_DOWN_POSITION;
import static com.spikes2212.robot2016.Constants.FOLDER_DOWN_SPEED;
import static com.spikes2212.robot2016.Constants.FOLDER_UP_POSITION;
import static com.spikes2212.robot2016.Constants.FOLDER_UP_SPEED;
import static com.spikes2212.robot2016.Constants.FREE_FALL_GRAVITY;
import static com.spikes2212.robot2016.Constants.LIFTING_PORTCULLIS_DISTANCE;
import static com.spikes2212.robot2016.Constants.LOW_BAR_DISTANCE;
import static com.spikes2212.robot2016.Constants.MOAT_DISTANCE;
import static com.spikes2212.robot2016.Constants.PICKER_ROLL_IN_SPEED;
import static com.spikes2212.robot2016.Constants.PICKER_ROLL_OUT_SPEED;
import static com.spikes2212.robot2016.Constants.RIGHT_DISTANCE_PER_PULSE;
import static com.spikes2212.robot2016.Constants.TRIZ_DISTANCE_PER_PULSE;
import static com.spikes2212.robot2016.Constants.TRIZ_DOWN_POSITION;
import static com.spikes2212.robot2016.Constants.TRIZ_SPEED;
import static com.spikes2212.robot2016.Constants.TRIZ_UP_POSITION;

import com.spikes2212.robot2016.Field.Defense;
import com.spikes2212.robot2016.Field.DefenseLocation;
import com.spikes2212.robot2016.Field.Direction;
import com.spikes2212.robot2016.Field.Goal;
import com.spikes2212.robot2016.RobotMap.DIO;
import com.spikes2212.robot2016.RobotMap.PWM;
import com.spikes2212.robot2016.RobotMap.USB;
import com.spikes2212.robot2016.commands.autonomous.Cross;
import com.spikes2212.robot2016.commands.autonomous.CrossAndDropAndReturn;
import com.spikes2212.robot2016.commands.autonomous.CrossAndReturn;
import com.spikes2212.robot2016.commands.autonomous.CrossAndScoreGoal;
import com.spikes2212.robot2016.commands.autonomous.CrossChevalDeFrise;
import com.spikes2212.robot2016.commands.autonomous.CrossLowBar;
import com.spikes2212.robot2016.commands.autonomous.CrossMoat;
import com.spikes2212.robot2016.commands.autonomous.CrossPortcullis;
import com.spikes2212.robot2016.commands.autonomous.CrossRamparts;
import com.spikes2212.robot2016.commands.autonomous.CrossRockWall;
import com.spikes2212.robot2016.commands.autonomous.CrossRoughTerrain;
import com.spikes2212.robot2016.commands.autonomous.ScoreGoalFromLocation1;
import com.spikes2212.robot2016.commands.autonomous.ScoreGoalFromLocation2;
import com.spikes2212.robot2016.commands.autonomous.ScoreGoalFromLocation3;
import com.spikes2212.robot2016.commands.autonomous.ScoreGoalFromLocation4;
import com.spikes2212.robot2016.commands.autonomous.ScoreGoalFromLocation5;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.drivetrain.PIDTurnDriveByAngle;
import com.spikes2212.robot2016.commands.drivetrain.StraightDriveBySpeedAndTime;
import com.spikes2212.robot2016.commands.drivetrain.TurnDriveBySpeedAndTime;
import com.spikes2212.robot2016.commands.drivetrain.fixed.FixedPIDStraightDriveDistanceWithEncoderDifference;
import com.spikes2212.robot2016.commands.drivetrain.fixed.FixedPIDStraightDriveDistanceWithGyro;
import com.spikes2212.robot2016.commands.drivetrain.fixed.FixedPIDStraightDriveDistanceWithTwoEncoders;
import com.spikes2212.robot2016.commands.folder.MoveFolderToShoot;
import com.spikes2212.robot2016.commands.folder.PIDMoveFolder;
import com.spikes2212.robot2016.commands.picker.RollOut;
import com.spikes2212.robot2016.commands.shooter.ShootByVoltage;
import com.spikes2212.robot2016.commands.triz.PIDMoveTriz;
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
//	public static Cameras cameras;

	Command autoCommand;

	SendableChooser defenseChooser;
	SendableChooser locationChooser;
	SendableChooser autoChooser;
	SendableChooser goalChooser;

	public void initConstantsFromConstantsClass() {
		SmartDashboard.putNumber("TRIZ_SPEED", Constants.TRIZ_SPEED);
		SmartDashboard.putNumber("FOLDER_UP_SPEED", Constants.FOLDER_UP_SPEED);
		SmartDashboard.putNumber("FOLDER_DOWN_SPEED", Constants.FOLDER_DOWN_SPEED);
		SmartDashboard.putNumber("PICKER_ROLL_IN_SPEED", Constants.PICKER_ROLL_IN_SPEED);
		SmartDashboard.putNumber("PICKER_ROLL_OUT_SPEED", Constants.PICKER_ROLL_OUT_SPEED);
		SmartDashboard.putNumber("SHOOTING_VOLTAGE", Constants.SHOOTING_VOLTAGE);

		SmartDashboard.putNumber("DRIVE_TO_PORTCULLIS_DISTANCE", Constants.DRIVE_TO_PORTCULLIS_DISTANCE);

		SmartDashboard.putNumber("LIFTING_PORTCULLIS_DISTANCE", Constants.LIFTING_PORTCULLIS_DISTANCE);

		SmartDashboard.putNumber("LOW_BAR_DISTANCE", Constants.LOW_BAR_DISTANCE);
		SmartDashboard.putNumber("AFTER_LIFTING_PORTCULLIS_DISTANCE", Constants.AFTER_LIFTING_PORTCULLIS_DISTANCE);
		SmartDashboard.putNumber("MOAT_DISTANCE", Constants.MOAT_DISTANCE);

		SmartDashboard.putNumber("TRIZ_UP_POSITION", Constants.TRIZ_UP_POSITION);
		SmartDashboard.putNumber("TRIZ_DOWN_POSITION", Constants.TRIZ_DOWN_POSITION);
		SmartDashboard.putNumber("FOLDER_UP_POSITION", Constants.FOLDER_UP_POSITION);
		SmartDashboard.putNumber("FOLDER_DOWN_POSITION", Constants.FOLDER_DOWN_POSITION);

		SmartDashboard.putNumber("FREE_FALL_GRAVITY", Constants.FREE_FALL_GRAVITY);

		SmartDashboard.putNumber("RIGHT_DISTANCE_PER_PULSE", Constants.RIGHT_DISTANCE_PER_PULSE);

		SmartDashboard.putNumber("TRIZ_DISTANCE_PER_PULSE", Constants.TRIZ_DISTANCE_PER_PULSE);

		SmartDashboard.putNumber("FOLDER_DISTANCE_PER_PULSE", Constants.FOLDER_DISTANCE_PER_PULSE);
	}

	public void getConstantsFromConstantsClass() {
		TRIZ_SPEED = SmartDashboard.getNumber("TRIZ_SPEED", Constants.TRIZ_SPEED);
		FOLDER_UP_SPEED = SmartDashboard.getNumber("FOLDER_UP_SPEED", Constants.FOLDER_UP_SPEED);
		FOLDER_DOWN_SPEED = SmartDashboard.getNumber("FOLDER_DOWN_SPEED", Constants.FOLDER_DOWN_SPEED);
		PICKER_ROLL_IN_SPEED = SmartDashboard.getNumber("PICKER_ROLL_IN_SPEED", Constants.PICKER_ROLL_IN_SPEED);
		PICKER_ROLL_OUT_SPEED = SmartDashboard.getNumber("PICKER_ROLL_OUT_SPEED", Constants.PICKER_ROLL_OUT_SPEED);
		PICKER_ROLL_OUT_SPEED = SmartDashboard.getNumber("SHOOTING_VOLTAGE", Constants.SHOOTING_VOLTAGE);

		DRIVE_TO_PORTCULLIS_DISTANCE = SmartDashboard.getNumber("DRIVE_TO_PORTCULLIS_DISTANCE",
				Constants.DRIVE_TO_PORTCULLIS_DISTANCE);

		LIFTING_PORTCULLIS_DISTANCE = SmartDashboard.getNumber("LIFTING_PORTCULLIS_DISTANCE",
				Constants.LIFTING_PORTCULLIS_DISTANCE);

		LOW_BAR_DISTANCE = SmartDashboard.getNumber("LOW_BAR_DISTANCE", Constants.LOW_BAR_DISTANCE);
		AFTER_LIFTING_PORTCULLIS_DISTANCE = SmartDashboard.getNumber("AFTER_LIFTING_PORTCULLIS_DISTANCE",
				Constants.AFTER_LIFTING_PORTCULLIS_DISTANCE);
		MOAT_DISTANCE = SmartDashboard.getNumber("MOAT_DISTANCE", Constants.MOAT_DISTANCE);

		TRIZ_UP_POSITION = SmartDashboard.getNumber("TRIZ_UP_POSITION", Constants.TRIZ_UP_POSITION);
		TRIZ_DOWN_POSITION = SmartDashboard.getNumber("TRIZ_DOWN_POSITION", Constants.TRIZ_DOWN_POSITION);
		FOLDER_UP_POSITION = SmartDashboard.getNumber("FOLDER_UP_POSITION", Constants.FOLDER_UP_POSITION);
		FOLDER_DOWN_POSITION = SmartDashboard.getNumber("FOLDER_DOWN_POSITION", Constants.FOLDER_DOWN_POSITION);

		FREE_FALL_GRAVITY = SmartDashboard.getNumber("FREE_FALL_GRAVITY", Constants.FREE_FALL_GRAVITY);

		RIGHT_DISTANCE_PER_PULSE = SmartDashboard.getNumber("RIGHT_DISTANCE_PER_PULSE",
				Constants.RIGHT_DISTANCE_PER_PULSE);

		TRIZ_DISTANCE_PER_PULSE = SmartDashboard.getNumber("TRIZ_DISTANCE_PER_PULSE",
				Constants.TRIZ_DISTANCE_PER_PULSE);

		FOLDER_DISTANCE_PER_PULSE = SmartDashboard.getNumber("FOLDER_DISTANCE_PER_PULSE",
				Constants.FOLDER_DISTANCE_PER_PULSE);

	}

	public void initConstantsFromAutonomousPackage() {
		CrossAndDropAndReturn.WAIT_TIME = SmartDashboard.getNumber("CrossAndDropAndReturn.WAIT_TIME",
				CrossAndDropAndReturn.WAIT_TIME);
		CrossAndReturn.WAIT_TIME = SmartDashboard.getNumber("CrossAndReturn.WAIT_TIME", CrossAndReturn.WAIT_TIME);
		CrossAndScoreGoal.WAIT_TIME = SmartDashboard.getNumber("CrossAndScoreGoal.WAIT_TIME",
				CrossAndScoreGoal.WAIT_TIME);
		CrossChevalDeFrise.DISTANCE = SmartDashboard.getNumber("CrossChevalDeFrise.DISTANCE",
				CrossChevalDeFrise.DISTANCE);
		CrossLowBar.DISTANCE = SmartDashboard.getNumber("CrossLowBar.DISTANCE", CrossLowBar.DISTANCE);
		CrossMoat.DISTANCE = SmartDashboard.getNumber("CrossMoat.DISTANCE", CrossMoat.DISTANCE);
		CrossPortcullis.ROTATE_ANGLE = SmartDashboard.getNumber("CrossPortcullis.ROTATE_ANGLE",
				CrossPortcullis.ROTATE_ANGLE);
		CrossRamparts.DISTANCE = SmartDashboard.getNumber("CrossRamparts.DISTANCE", CrossRamparts.DISTANCE);
		CrossRockWall.DISTANCE = SmartDashboard.getNumber("CrossRockWall.DISTANCE", CrossRockWall.DISTANCE);
		CrossRoughTerrain.DISTANCE = SmartDashboard.getNumber("CrossRoughTerrain.DISTANCE", CrossRoughTerrain.DISTANCE);

		ScoreGoalFromLocation1.ANGLE = SmartDashboard.getNumber("ScoreGoalFromLocation1.ANGLE",
				ScoreGoalFromLocation1.ANGLE);
		ScoreGoalFromLocation1.ROTATE_ANGLE = SmartDashboard.getNumber("ScoreGoalFromLocation1.ROTATE_ANGLE",
				ScoreGoalFromLocation1.ROTATE_ANGLE);
		ScoreGoalFromLocation1.FIRST_DISTANCE = SmartDashboard.getNumber("ScoreGoalFromLocation1.FIRST_DISTANCE",
				ScoreGoalFromLocation1.FIRST_DISTANCE);
		ScoreGoalFromLocation1.SECOND_DISTANCE = SmartDashboard.getNumber("ScoreGoalFromLocation1.SECOND_DISTANCE",
				ScoreGoalFromLocation1.SECOND_DISTANCE);
		ScoreGoalFromLocation1.TIMEOUT = SmartDashboard.getNumber("ScoreGoalFromLocation1.TIMEOUT",
				ScoreGoalFromLocation1.TIMEOUT);

		ScoreGoalFromLocation2.ANGLE = SmartDashboard.getNumber("ScoreGoalFromLocation2.ANGLE",
				ScoreGoalFromLocation2.ANGLE);
		ScoreGoalFromLocation2.ROTATE_ANGLE = SmartDashboard.getNumber("ScoreGoalFromLocation2.ROTATE_ANGLE",
				ScoreGoalFromLocation2.ROTATE_ANGLE);
		ScoreGoalFromLocation2.FIRST_DISTANCE = SmartDashboard.getNumber("ScoreGoalFromLocation2.FIRST_DISTANCE",
				ScoreGoalFromLocation2.FIRST_DISTANCE);
		ScoreGoalFromLocation2.SECOND_DISTANCE = SmartDashboard.getNumber("ScoreGoalFromLocation2.SECOND_DISTANCE",
				ScoreGoalFromLocation2.SECOND_DISTANCE);
		ScoreGoalFromLocation2.TIMEOUT = SmartDashboard.getNumber("ScoreGoalFromLocation2.TIMEOUT",
				ScoreGoalFromLocation2.TIMEOUT);

		ScoreGoalFromLocation3.ANGLE = SmartDashboard.getNumber("ScoreGoalFromLocation3.ANGLE",
				ScoreGoalFromLocation3.ANGLE);
		ScoreGoalFromLocation3.ROTATE_ANGLE = SmartDashboard.getNumber("ScoreGoalFromLocation3.ROTATE_ANGLE",
				ScoreGoalFromLocation3.ROTATE_ANGLE);
		ScoreGoalFromLocation3.FIRST_DISTANCE = SmartDashboard.getNumber("ScoreGoalFromLocation3.FIRST_DISTANCE",
				ScoreGoalFromLocation3.FIRST_DISTANCE);
		ScoreGoalFromLocation3.SECOND_DISTANCE = SmartDashboard.getNumber("ScoreGoalFromLocation3.SECOND_DISTANCE",
				ScoreGoalFromLocation3.SECOND_DISTANCE);
		ScoreGoalFromLocation3.TIMEOUT = SmartDashboard.getNumber("ScoreGoalFromLocation3.TIMEOUT",
				ScoreGoalFromLocation3.TIMEOUT);

		ScoreGoalFromLocation4.ANGLE = SmartDashboard.getNumber("ScoreGoalFromLocation4.ANGLE",
				ScoreGoalFromLocation4.ANGLE);
		ScoreGoalFromLocation4.ROTATE_ANGLE = SmartDashboard.getNumber("ScoreGoalFromLocation4.ROTATE_ANGLE",
				ScoreGoalFromLocation4.ROTATE_ANGLE);
		ScoreGoalFromLocation4.FIRST_DISTANCE = SmartDashboard.getNumber("ScoreGoalFromLocation4.FIRST_DISTANCE",
				ScoreGoalFromLocation4.FIRST_DISTANCE);
		ScoreGoalFromLocation4.SECOND_DISTANCE = SmartDashboard.getNumber("ScoreGoalFromLocation4.SECOND_DISTANCE",
				ScoreGoalFromLocation4.SECOND_DISTANCE);
		ScoreGoalFromLocation4.TIMEOUT = SmartDashboard.getNumber("ScoreGoalFromLocation4.TIMEOUT",
				ScoreGoalFromLocation4.TIMEOUT);

		ScoreGoalFromLocation5.ANGLE = SmartDashboard.getNumber("ScoreGoalFromLocation5.ANGLE",
				ScoreGoalFromLocation5.ANGLE);
		ScoreGoalFromLocation5.ROTATE_ANGLE = SmartDashboard.getNumber("ScoreGoalFromLocation5.ROTATE_ANGLE",
				ScoreGoalFromLocation5.ROTATE_ANGLE);
		ScoreGoalFromLocation5.FIRST_DISTANCE = SmartDashboard.getNumber("ScoreGoalFromLocation5.FIRST_DISTANCE",
				ScoreGoalFromLocation5.FIRST_DISTANCE);
		ScoreGoalFromLocation5.SECOND_DISTANCE = SmartDashboard.getNumber("ScoreGoalFromLocation5.SECOND_DISTANCE",
				ScoreGoalFromLocation5.SECOND_DISTANCE);
		ScoreGoalFromLocation5.TIMEOUT = SmartDashboard.getNumber("ScoreGoalFromLocation5.TIMEOUT",
				ScoreGoalFromLocation5.TIMEOUT);
	}

	public void getConstantsFromAutonomousPackage() {
		SmartDashboard.getNumber("CrossAndDropAndReturn.WAIT_TIME", CrossAndDropAndReturn.WAIT_TIME);
		SmartDashboard.getNumber("CrossAndReturn.WAIT_TIME", CrossAndReturn.WAIT_TIME);
		SmartDashboard.getNumber("CrossAndScoreGoal.WAIT_TIME", CrossAndScoreGoal.WAIT_TIME);
		SmartDashboard.getNumber("CrossChevalDeFrise.DISTANCE", CrossChevalDeFrise.DISTANCE);
		SmartDashboard.getNumber("CrossLowBar.DISTANCE", CrossLowBar.DISTANCE);
		SmartDashboard.getNumber("CrossMoat.DISTANCE", CrossMoat.DISTANCE);
		SmartDashboard.getNumber("CrossPortcullis.ROTATE_ANGLE", CrossPortcullis.ROTATE_ANGLE);
		SmartDashboard.getNumber("CrossRamparts.DISTANCE", CrossRamparts.DISTANCE);
		SmartDashboard.getNumber("CrossRockWall.DISTANCE", CrossRockWall.DISTANCE);
		SmartDashboard.getNumber("CrossRoughTerrain.DISTANCE", CrossRoughTerrain.DISTANCE);
		SmartDashboard.getNumber("ScoreGoalFromLocation1.ANGLE", ScoreGoalFromLocation1.ANGLE);
		SmartDashboard.getNumber("ScoreGoalFromLocation1.ROTATE_ANGLE", ScoreGoalFromLocation1.ROTATE_ANGLE);
		SmartDashboard.getNumber("ScoreGoalFromLocation1.FIRST_DISTANCE", ScoreGoalFromLocation1.FIRST_DISTANCE);
		SmartDashboard.getNumber("ScoreGoalFromLocation1.SECOND_DISTANCE", ScoreGoalFromLocation1.SECOND_DISTANCE);
		SmartDashboard.getNumber("ScoreGoalFromLocation1.TIMEOUT", ScoreGoalFromLocation1.TIMEOUT);

		SmartDashboard.getNumber("ScoreGoalFromLocation2.ANGLE", ScoreGoalFromLocation2.ANGLE);
		SmartDashboard.getNumber("ScoreGoalFromLocation2.ROTATE_ANGLE", ScoreGoalFromLocation2.ROTATE_ANGLE);
		SmartDashboard.getNumber("ScoreGoalFromLocation2.FIRST_DISTANCE", ScoreGoalFromLocation2.FIRST_DISTANCE);
		SmartDashboard.getNumber("ScoreGoalFromLocation2.SECOND_DISTANCE", ScoreGoalFromLocation2.SECOND_DISTANCE);
		SmartDashboard.getNumber("ScoreGoalFromLocation2.TIMEOUT", ScoreGoalFromLocation2.TIMEOUT);

		SmartDashboard.getNumber("ScoreGoalFromLocation3.ANGLE", ScoreGoalFromLocation3.ANGLE);
		SmartDashboard.getNumber("ScoreGoalFromLocation3.ROTATE_ANGLE", ScoreGoalFromLocation3.ROTATE_ANGLE);
		SmartDashboard.getNumber("ScoreGoalFromLocation3.FIRST_DISTANCE", ScoreGoalFromLocation3.FIRST_DISTANCE);
		SmartDashboard.getNumber("ScoreGoalFromLocation3.SECOND_DISTANCE", ScoreGoalFromLocation3.SECOND_DISTANCE);
		SmartDashboard.getNumber("ScoreGoalFromLocation3.TIMEOUT", ScoreGoalFromLocation3.TIMEOUT);

		SmartDashboard.getNumber("ScoreGoalFromLocation4.ANGLE", ScoreGoalFromLocation4.ANGLE);
		SmartDashboard.getNumber("ScoreGoalFromLocation4.ROTATE_ANGLE", ScoreGoalFromLocation4.ROTATE_ANGLE);
		SmartDashboard.getNumber("ScoreGoalFromLocation4.FIRST_DISTANCE", ScoreGoalFromLocation4.FIRST_DISTANCE);
		SmartDashboard.getNumber("ScoreGoalFromLocation4.SECOND_DISTANCE", ScoreGoalFromLocation4.SECOND_DISTANCE);
		SmartDashboard.getNumber("ScoreGoalFromLocation4.TIMEOUT", ScoreGoalFromLocation4.TIMEOUT);

		SmartDashboard.getNumber("ScoreGoalFromLocation5.ANGLE", ScoreGoalFromLocation5.ANGLE);
		SmartDashboard.getNumber("ScoreGoalFromLocation5.ROTATE_ANGLE", ScoreGoalFromLocation5.ROTATE_ANGLE);
		SmartDashboard.getNumber("ScoreGoalFromLocation5.FIRST_DISTANCE", ScoreGoalFromLocation5.FIRST_DISTANCE);
		SmartDashboard.getNumber("ScoreGoalFromLocation5.SECOND_DISTANCE", ScoreGoalFromLocation5.SECOND_DISTANCE);
		SmartDashboard.getNumber("ScoreGoalFromLocation5.TIMEOUT", ScoreGoalFromLocation5.TIMEOUT);
	}

	public void initConstantsfromFolderPackage() {
		SmartDashboard.putNumber("MoveFolderToShoot.POSITION", MoveFolderToShoot.POSITION);
		SmartDashboard.putNumber("MoveFolderToShoot.ABSOLUTE", MoveFolderToShoot.ABSOLUTE);
		SmartDashboard.putNumber("PIDMoveFolder.KP", PIDMoveFolder.KP);
		SmartDashboard.putNumber("PIDMoveFolder.KI", PIDMoveFolder.KI);
		SmartDashboard.putNumber("PIDMoveFolder.KD", PIDMoveFolder.KD);
	}

	public void getConstantsfromFolderPackage() {
		MoveFolderToShoot.POSITION = SmartDashboard.getNumber("MoveFolderToShoot.POSITION", MoveFolderToShoot.POSITION);
		MoveFolderToShoot.ABSOLUTE = SmartDashboard.getNumber("MoveFolderToShoot.ABSOLUTE", MoveFolderToShoot.ABSOLUTE);
		PIDMoveFolder.KP = SmartDashboard.getNumber("PIDMoveFolder.KP", PIDMoveFolder.KP);
		PIDMoveFolder.KI = SmartDashboard.getNumber("PIDMoveFolder.KI", PIDMoveFolder.KI);
		PIDMoveFolder.KD = SmartDashboard.getNumber("PIDMoveFolder.KD", PIDMoveFolder.KD);
	}

	public void initConstantsfromPickerPackage() {
		SmartDashboard.putNumber("RollOut.TIMEOUT", RollOut.TIMEOUT);
	}

	public void getConstantsfromPickerPackage() {
		RollOut.TIMEOUT = SmartDashboard.getNumber("RollOut.TIMEOUT", RollOut.TIMEOUT);
	}

	public void initConstantsFromShooterPackage() {
		SmartDashboard.putNumber("ShootByVoltage.ACCELERATION_TIME", ShootByVoltage.ACCELERATION_TIME);
		SmartDashboard.putNumber("ShootByVoltage.ROLL_IN_TIME", ShootByVoltage.ROLL_IN_TIME);
	}

	public void getConstantsFromShooterPackage() {
		ShootByVoltage.ACCELERATION_TIME = SmartDashboard.getNumber("ShootByVoltage.ACCELERATION_TIME",
				ShootByVoltage.ACCELERATION_TIME);
		ShootByVoltage.ROLL_IN_TIME = SmartDashboard.getNumber("ShootByVoltage.ROLL_IN_TIME",
				ShootByVoltage.ROLL_IN_TIME);
	}

	public void initConstantsFromTrizPackage() {
		SmartDashboard.putNumber("PIDMoveTriz.KP", PIDMoveTriz.KP);
		SmartDashboard.putNumber("PIDMoveTriz.KI", PIDMoveTriz.KI);
		SmartDashboard.putNumber("PIDMoveTriz.KD", PIDMoveTriz.KD);
		SmartDashboard.putNumber("PIDMoveTriz.ABSOLUTE_TOLERANCE", PIDMoveTriz.ABSOLUTE_TOLERANCE);
	}

	public void getConstantsFromTrizPackage() {
		PIDMoveTriz.KP = SmartDashboard.getNumber("PIDMoveTriz.KP", PIDMoveTriz.KP);
		PIDMoveTriz.KI = SmartDashboard.getNumber("PIDMoveTriz.KI", PIDMoveTriz.KI);
		PIDMoveTriz.KD = SmartDashboard.getNumber("PIDMoveTriz.KD", PIDMoveTriz.KD);
		PIDMoveTriz.ABSOLUTE_TOLERANCE = SmartDashboard.getNumber("PIDMoveTriz.ABSOLUTE_TOLERANCE",
				PIDMoveTriz.ABSOLUTE_TOLERANCE);
	}

	public void initConstantsFromDeivetrainPackage() {
		SmartDashboard.putNumber("PIDStraightDriveByDistance.KP", PIDStraightDriveByDistance.KP);
		SmartDashboard.putNumber("PIDStraightDriveByDistance.KI", PIDStraightDriveByDistance.KI);
		SmartDashboard.putNumber("PIDStraightDriveByDistance.KD", PIDStraightDriveByDistance.KD);
		SmartDashboard.putNumber("PIDStraightDriveByDistance.ABSOLUTE_TOLERANCE",
				PIDStraightDriveByDistance.ABSOLUTE_TOLERANCE);
		SmartDashboard.putNumber("PIDTurnDriveByAngle.KP", PIDTurnDriveByAngle.KP);
		SmartDashboard.putNumber("PIDTurnDriveByAngle.KI", PIDTurnDriveByAngle.KI);
		SmartDashboard.putNumber("PIDTurnDriveByAngle.KD", PIDTurnDriveByAngle.KD);
		SmartDashboard.putNumber("PIDTurnDriveByAngle.ABSOLUTE_TOLERANCE", PIDTurnDriveByAngle.ABSOLUTE_TOLERANCE);

	}

	public void getConstantsFromDeivetrainPackage() {
		PIDStraightDriveByDistance.KP = SmartDashboard.getNumber("PIDStraightDriveByDistance.KP",
				PIDStraightDriveByDistance.KP);
		PIDStraightDriveByDistance.KI = SmartDashboard.getNumber("PIDStraightDriveByDistance.KI",
				PIDStraightDriveByDistance.KI);
		PIDStraightDriveByDistance.KD = SmartDashboard.getNumber("PIDStraightDriveByDistance.KD",
				PIDStraightDriveByDistance.KD);
		PIDStraightDriveByDistance.ABSOLUTE_TOLERANCE = SmartDashboard.getNumber(
				"PIDStraightDriveByDistance.ABSOLUTE_TOLERANCE", PIDStraightDriveByDistance.ABSOLUTE_TOLERANCE);
		PIDTurnDriveByAngle.KP = SmartDashboard.getNumber("PIDTurnDriveByAngle.KP", PIDTurnDriveByAngle.KP);
		PIDTurnDriveByAngle.KI = SmartDashboard.getNumber("PIDTurnDriveByAngle.KI", PIDTurnDriveByAngle.KI);
		PIDTurnDriveByAngle.KD = SmartDashboard.getNumber("PIDTurnDriveByAngle.KD", PIDTurnDriveByAngle.KD);
		PIDTurnDriveByAngle.ABSOLUTE_TOLERANCE = SmartDashboard.getNumber("PIDTurnDriveByAngle.ABSOLUTE_TOLERANCE",
				PIDTurnDriveByAngle.ABSOLUTE_TOLERANCE);
	}

	public void initConstantsFromFixedDrivetrainPackage() {
		SmartDashboard.putNumber("PIDStraightDriveByDistance.DISTANCE",
				PIDStraightDriveByDistance.DISTANCE);
		SmartDashboard.putNumber("PIDTurnDriveByAngle.ANGLE",
				PIDTurnDriveByAngle.ANGLE);
		SmartDashboard.putNumber("StraightDriveBySpeedAndTime.speed",
				StraightDriveBySpeedAndTime.speed);
		SmartDashboard.putNumber("StraightDriveBySpeedAndTime.time",
				StraightDriveBySpeedAndTime.time);
		SmartDashboard.putNumber("TurnDriveBySpeedAndTime.spee",
				TurnDriveBySpeedAndTime.speed);
		SmartDashboard.putNumber("TurnDriveBySpeedAndTime.time",
				TurnDriveBySpeedAndTime.time);
		
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithEncoderDifference.KP_STRAIGHT",
				FixedPIDStraightDriveDistanceWithEncoderDifference.KP_STRAIGHT);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithEncoderDifference.KI_STRAIGHT",
				FixedPIDStraightDriveDistanceWithEncoderDifference.KI_STRAIGHT);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithEncoderDifference.KD_STRAIGHT",
				FixedPIDStraightDriveDistanceWithEncoderDifference.KD_STRAIGHT);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithEncoderDifference.KP_TURN",
				FixedPIDStraightDriveDistanceWithEncoderDifference.KP_TURN);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithEncoderDifference.KI_TURN",
				FixedPIDStraightDriveDistanceWithEncoderDifference.KI_TURN);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithEncoderDifference.KD_TURN",
				FixedPIDStraightDriveDistanceWithEncoderDifference.KD_TURN);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithEncoderDifference.TOLERANCE_STRAIGHT",
				FixedPIDStraightDriveDistanceWithEncoderDifference.TOLERANCE_STRAIGHT);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithEncoderDifference.TOLERANCE_TURN",
				FixedPIDStraightDriveDistanceWithEncoderDifference.TOLERANCE_TURN);

		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithGyro.KP_STRAIGHT",
				FixedPIDStraightDriveDistanceWithGyro.KP_STRAIGHT);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithGyro.KI_STRAIGHT",
				FixedPIDStraightDriveDistanceWithGyro.KI_STRAIGHT);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithGyro.KD_STRAIGHT",
				FixedPIDStraightDriveDistanceWithGyro.KD_STRAIGHT);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithGyro.KP_TURN",
				FixedPIDStraightDriveDistanceWithGyro.KP_TURN);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithGyro.KI_TURN",
				FixedPIDStraightDriveDistanceWithGyro.KI_TURN);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithGyro.KD_TURN",
				FixedPIDStraightDriveDistanceWithGyro.KD_TURN);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithGyro.TOLERANCE_STRAIGHT",
				FixedPIDStraightDriveDistanceWithGyro.TOLERANCE_STRAIGHT);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithGyro.TOLERANCE_TURN",
				FixedPIDStraightDriveDistanceWithGyro.TOLERANCE_TURN);

		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithTwoEncoders.KP_LEFT",
				FixedPIDStraightDriveDistanceWithTwoEncoders.KP_LEFT);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithTwoEncoders.KI_LEFT",
				FixedPIDStraightDriveDistanceWithTwoEncoders.KI_LEFT);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithTwoEncoders.KD_LEFT",
				FixedPIDStraightDriveDistanceWithTwoEncoders.KD_LEFT);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithTwoEncoders.KP_RIGHT",
				FixedPIDStraightDriveDistanceWithTwoEncoders.KP_RIGHT);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithTwoEncoders.KI_RIGHT",
				FixedPIDStraightDriveDistanceWithTwoEncoders.KI_RIGHT);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithTwoEncoders.KD_RIGHT",
				FixedPIDStraightDriveDistanceWithTwoEncoders.KD_RIGHT);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithTwoEncoders.TOLERANCE_LEFT",
				FixedPIDStraightDriveDistanceWithTwoEncoders.TOLERANCE_LEFT);
		SmartDashboard.putNumber("FixedPIDStraightDriveDistanceWithTwoEncoders.TOLERANCE_RIGHT",
				FixedPIDStraightDriveDistanceWithTwoEncoders.TOLERANCE_RIGHT);
		
	}

	public void getConstantsFromFixedDrivetrainPackage() {
		PIDStraightDriveByDistance.DISTANCE = 
				SmartDashboard.getNumber("PIDStraightDriveByDistance.DISTANCE");
		PIDTurnDriveByAngle.ANGLE = SmartDashboard.getNumber("PIDTurnDriveByAngle.ANGLE");
		TurnDriveBySpeedAndTime.speed = SmartDashboard.getNumber("TurnDriveBySpeedAndTime.speed");
		TurnDriveBySpeedAndTime.time = SmartDashboard.getNumber("TurnDriveBySpeedAndTime.time");
		
		FixedPIDStraightDriveDistanceWithEncoderDifference.KP_STRAIGHT = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithEncoderDifference.KP_STRAIGHT",
				FixedPIDStraightDriveDistanceWithEncoderDifference.KP_STRAIGHT);
		FixedPIDStraightDriveDistanceWithEncoderDifference.KI_STRAIGHT = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithEncoderDifference.KI_STRAIGHT",
				FixedPIDStraightDriveDistanceWithEncoderDifference.KI_STRAIGHT);
		FixedPIDStraightDriveDistanceWithEncoderDifference.KD_STRAIGHT = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithEncoderDifference.KD_STRAIGHT",
				FixedPIDStraightDriveDistanceWithEncoderDifference.KD_STRAIGHT);
		FixedPIDStraightDriveDistanceWithEncoderDifference.KP_TURN = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithEncoderDifference.KP_TURN",
				FixedPIDStraightDriveDistanceWithEncoderDifference.KP_TURN);
		FixedPIDStraightDriveDistanceWithEncoderDifference.KI_TURN = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithEncoderDifference.KI_TURN",
				FixedPIDStraightDriveDistanceWithEncoderDifference.KI_TURN);
		FixedPIDStraightDriveDistanceWithEncoderDifference.KD_TURN = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithEncoderDifference.KD_TURN",
				FixedPIDStraightDriveDistanceWithEncoderDifference.KD_TURN);
		FixedPIDStraightDriveDistanceWithEncoderDifference.TOLERANCE_STRAIGHT = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithEncoderDifference.TOLERANCE_STRAIGHT",
				FixedPIDStraightDriveDistanceWithEncoderDifference.TOLERANCE_STRAIGHT);
		FixedPIDStraightDriveDistanceWithEncoderDifference.TOLERANCE_TURN = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithEncoderDifference.TOLERANCE_TURN",
				FixedPIDStraightDriveDistanceWithEncoderDifference.TOLERANCE_TURN);

		FixedPIDStraightDriveDistanceWithGyro.KP_STRAIGHT = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithGyro.KP_STRAIGHT", FixedPIDStraightDriveDistanceWithGyro.KP_STRAIGHT);
		FixedPIDStraightDriveDistanceWithGyro.KI_STRAIGHT = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithGyro.KI_STRAIGHT", FixedPIDStraightDriveDistanceWithGyro.KI_STRAIGHT);
		FixedPIDStraightDriveDistanceWithGyro.KD_STRAIGHT = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithGyro.KD_STRAIGHT", FixedPIDStraightDriveDistanceWithGyro.KD_STRAIGHT);
		FixedPIDStraightDriveDistanceWithGyro.KP_TURN = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithGyro.KP_TURN", FixedPIDStraightDriveDistanceWithGyro.KP_TURN);
		FixedPIDStraightDriveDistanceWithGyro.KI_TURN = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithGyro.KI_TURN", FixedPIDStraightDriveDistanceWithGyro.KI_TURN);
		FixedPIDStraightDriveDistanceWithGyro.KD_TURN = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithGyro.KD_TURN", FixedPIDStraightDriveDistanceWithGyro.KD_TURN);
		FixedPIDStraightDriveDistanceWithGyro.TOLERANCE_STRAIGHT = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithGyro.TOLERANCE_STRAIGHT",
				FixedPIDStraightDriveDistanceWithGyro.TOLERANCE_STRAIGHT);
		FixedPIDStraightDriveDistanceWithGyro.TOLERANCE_TURN = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithGyro.TOLERANCE_TURN",
				FixedPIDStraightDriveDistanceWithGyro.TOLERANCE_TURN);

		FixedPIDStraightDriveDistanceWithTwoEncoders.KP_LEFT = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithTwoEncoders.KP_LEFT",
				FixedPIDStraightDriveDistanceWithTwoEncoders.KP_LEFT);
		FixedPIDStraightDriveDistanceWithTwoEncoders.KI_LEFT = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithTwoEncoders.KI_LEFT",
				FixedPIDStraightDriveDistanceWithTwoEncoders.KI_LEFT);
		FixedPIDStraightDriveDistanceWithTwoEncoders.KD_LEFT = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithTwoEncoders.KD_LEFT",
				FixedPIDStraightDriveDistanceWithTwoEncoders.KD_LEFT);
		FixedPIDStraightDriveDistanceWithTwoEncoders.KP_RIGHT = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithTwoEncoders.KP_RIGHT",
				FixedPIDStraightDriveDistanceWithTwoEncoders.KP_RIGHT);
		FixedPIDStraightDriveDistanceWithTwoEncoders.KI_RIGHT = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithTwoEncoders.KI_RIGHT",
				FixedPIDStraightDriveDistanceWithTwoEncoders.KI_RIGHT);
		FixedPIDStraightDriveDistanceWithTwoEncoders.KD_RIGHT = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithTwoEncoders.KD_RIGHT",
				FixedPIDStraightDriveDistanceWithTwoEncoders.KD_RIGHT);
		FixedPIDStraightDriveDistanceWithTwoEncoders.TOLERANCE_LEFT = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithTwoEncoders.TOLERANCE_LEFT",
				FixedPIDStraightDriveDistanceWithTwoEncoders.TOLERANCE_LEFT);
		FixedPIDStraightDriveDistanceWithTwoEncoders.TOLERANCE_RIGHT = SmartDashboard.getNumber(
				"FixedPIDStraightDriveDistanceWithTwoEncoders.TOLERANCE_RIGHT",
				FixedPIDStraightDriveDistanceWithTwoEncoders.TOLERANCE_RIGHT);

	}

	public void testAutonomousCommands() {
		SmartDashboard
				.putData(new Cross((Defense) defenseChooser.getSelected(), (Direction) locationChooser.getSelected()));
		SmartDashboard.putData(new CrossAndDropAndReturn((Defense) defenseChooser.getSelected()));
		SmartDashboard.putData(new CrossAndReturn((Defense) defenseChooser.getSelected()));
		SmartDashboard.putData(new CrossAndScoreGoal((Defense) defenseChooser.getSelected(),
				(DefenseLocation) locationChooser.getSelected(), (Goal) goalChooser.getSelected()));
		SmartDashboard.putData(new CrossChevalDeFrise((Direction) locationChooser.getSelected()));
		SmartDashboard.putData(new CrossLowBar((Direction) locationChooser.getSelected()));
		SmartDashboard.putData(new CrossMoat((Direction) locationChooser.getSelected()));
		SmartDashboard.putData(new CrossPortcullis((Direction) locationChooser.getSelected()));
		SmartDashboard.putData(new CrossRamparts((Direction) locationChooser.getSelected()));
		SmartDashboard.putData(new CrossRockWall((Direction) locationChooser.getSelected()));
		SmartDashboard.putData(new CrossRoughTerrain((Direction) locationChooser.getSelected()));
		SmartDashboard.putData(
				new ScoreGoalFromLocation1((Defense) defenseChooser.getSelected(), (Goal) goalChooser.getSelected()));
		SmartDashboard.putData(
				new ScoreGoalFromLocation2((Defense) defenseChooser.getSelected(), (Goal) goalChooser.getSelected()));
		SmartDashboard.putData(
				new ScoreGoalFromLocation3((Defense) defenseChooser.getSelected(), (Goal) goalChooser.getSelected()));
		SmartDashboard.putData(
				new ScoreGoalFromLocation4((Defense) defenseChooser.getSelected(), (Goal) goalChooser.getSelected()));
		SmartDashboard.putData(
				new ScoreGoalFromLocation5((Defense) defenseChooser.getSelected(), (Goal) goalChooser.getSelected()));
	}

	public void testDriveTrainCommands() {
		SmartDashboard.putData(new PIDStraightDriveByDistance());
		SmartDashboard.putData(new PIDTurnDriveByAngle());
		SmartDashboard.putData(new StraightDriveBySpeedAndTime());
		SmartDashboard.putData(new TurnDriveBySpeedAndTime());
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		gyro = new ADXRS450_Gyro();
		accelerometer = new BuiltInAccelerometer();
		left = new Gearbox(PWM.LEFT_FRONT_VICTOR, PWM.LEFT_REAR_VICTOR, DIO.LEFT_ENCODER_A, DIO.LEFT_ENCODER_B);
		right = new Gearbox(PWM.RIGHT_FRONT_VICTOR, PWM.RIGHT_REAR_VICTOR, DIO.RIGHT_ENCODER_A, DIO.RIGHT_ENCODER_B);
		drivetrain = new Drivetrain(left, right, gyro, accelerometer);
		triz = new Triz(PWM.TRIZ_MOTOR, DIO.TRIZ_UP, DIO.TRIZ_DOWN, DIO.TRIZ_UNDER_PORTCULLIS, DIO.TRIZ_ENCODER_A,
				DIO.TRIZ_ENCODER_B);
		shooter = new Shooter(PWM.SHOOTER_MOTOR);
		picker = new Picker(PWM.PICKER_MOTOR, DIO.BALL_INSIDE);
		folder = new Folder(PWM.FOLDER_MOTOR, DIO.FOLDER_UP, DIO.FOLDER_DOWN, DIO.FOLDER_ENCODER_A,
				DIO.FOLDER_ENCODER_B);
//		cameras = new Cameras(USB.FRONT_CAMERA, USB.REAR_CAMERA);
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
		autoChooser.addDefault("Cross & Return", "CrossAndReturn");
		autoChooser.addObject("Cross, drop & Return", "CrossAndDropAndReturn");
		autoChooser.addObject("Cross & score low", "CrossAndScoreLow");
		autoChooser.addObject("Cross & score high", "CrossAndScoreHigh");
		goalChooser = new SendableChooser();
		goalChooser.addObject("High", Goal.HIGH);
		goalChooser.addObject("Low", Goal.LOW);

		/*
		 * The values that were added here, are here thanks to tedious and
		 * tiring work of Saar. Jesus, please, do NOT remove any of them. Not
		 * from this function, nor from teleop. Comment them out.
		 */
		/*
		 * Change this line of code and re-upload the code every time you want
		 * to calibrate a different set of constants. Don't forget to change the
		 * equivalent line in teleopPeriodic!!
		 */
		initConstantsFromConstantsClass();
		initConstantsFromFixedDrivetrainPackage();
		testDriveTrainCommands();
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
	public void autonomousInit() {
		try {
			Defense defense = (Defense) defenseChooser.getSelected();
			DefenseLocation location = (DefenseLocation) locationChooser.getSelected();
			switch ((String) autoChooser.getSelected()) {
			case "CrossAndReturn":
				autoCommand = new CrossAndReturn(defense);
				break;
			case "CrossAndDropAndReturn":
				autoCommand = new CrossAndDropAndReturn(defense);
				break;
			case "CrossAndScoreLow":
				autoCommand = new CrossAndScoreGoal(defense, location, Goal.LOW);
				break;
			case "CrossAndScoreHigh":
				autoCommand = new CrossAndScoreGoal(defense, location, Goal.HIGH);
				break;
			default:
				autoCommand = new CommandGroup();
				break;
			}
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
	}

	@Override
	public void teleopInit() {
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		/*
		 * Change this line of code and re-upload the code every time you want
		 * to calibrate a different set of constants. Don't forget to change the
		 * equivalent line in robotInit!!
		 */
		getConstantsFromConstantsClass();
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

}
