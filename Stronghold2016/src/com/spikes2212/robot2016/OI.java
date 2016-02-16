package com.spikes2212.robot2016;

import com.spikes2212.robot2016.commands.drivetrain.JoystickForwardDrive;
import com.spikes2212.robot2016.commands.drivetrain.JoystickTurnDrive;
import com.spikes2212.robot2016.commands.folder.JoystickMoveFolder;
import com.spikes2212.robot2016.commands.folder.MoveFolderToShoot;
import com.spikes2212.robot2016.commands.picker.RollBallIn;
import com.spikes2212.robot2016.commands.picker.RollIn;
import com.spikes2212.robot2016.commands.picker.RollOut;
import com.spikes2212.robot2016.commands.shooter.JoystickRotateShooter;
import com.spikes2212.robot2016.commands.triz.JoystickMoveTriz;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI /* GEVALD */ {
	private final Joystick leftDriver = new Joystick(0);
	private final Joystick rightDriver = new Joystick(1);
	private final Joystick rightNavigator = new Joystick(2);

	public OI() {
		new JoystickButton(rightDriver, 1).toggleWhenPressed(new JoystickForwardDrive(this::getRightStraight));
		new JoystickButton(rightDriver, 3).toggleWhenPressed(new JoystickTurnDrive(this::getRightTurn));
		// new JoystickButton(rightDriver, 7).whenPressed(new FrontStream());
		// new JoystickButton(rightDriver, 8).whenPressed(new RearStream());
		// new JoystickButton(rightDriver, 9).whenPressed(new StopCameras());
		new JoystickButton(rightNavigator, 5).toggleWhenPressed(new JoystickMoveTriz(this::getNavigatorStraight));
		new JoystickButton(rightNavigator, 1).toggleWhenPressed(new RollBallIn());
		new JoystickButton(rightNavigator, 7).toggleWhenPressed(new RollIn(3));
		new JoystickButton(rightNavigator, 3).toggleWhenPressed(new RollOut());
		new JoystickButton(rightNavigator, 6).toggleWhenPressed(new JoystickMoveFolder(this::getNavigatorStraight));
		new JoystickButton(rightNavigator, 4).toggleWhenPressed(new JoystickRotateShooter(this::getNavigatorStraight));
		new JoystickButton(rightNavigator, 2).whenPressed(new MoveFolderToShoot());
		new JoystickButton(rightNavigator, 9).toggleWhenPressed(new JoystickMoveFolder(this::getNavigatorStraight));
	}

	private double squareInput(double input) {
		return input * Math.abs(input);
	}

	public double getLeftStraight() {
		return squareInput(-leftDriver.getY());
	}

	public double getRightStraight() {
		return squareInput(-rightDriver.getY());
	}

	public double getRightTurn() {
		return squareInput(rightDriver.getX());
	}

	public double getNavigatorStraight() {
		return -rightNavigator.getY();
	}

}
