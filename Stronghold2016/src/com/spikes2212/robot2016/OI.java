package com.spikes2212.robot2016;

import com.spikes2212.robot2016.Field.Goal;
import com.spikes2212.robot2016.commands.autonomous.ScoreGoal;
import com.spikes2212.robot2016.commands.drivetrain.JoystickArcadeDrive;
import com.spikes2212.robot2016.commands.drivetrain.SetDrivetrainMaximumSpeed;
import com.spikes2212.robot2016.commands.folder.ExpandFolder;
import com.spikes2212.robot2016.commands.folder.RetractFolder;
import com.spikes2212.robot2016.commands.picker.RollOut;
import com.spikes2212.robot2016.commands.shooter.ShootByVoltage;
import com.spikes2212.robot2016.commands.triz.ExpandTriz;
import com.spikes2212.robot2016.commands.triz.RetractTriz;

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

	private JoystickButton slowerButton = new JoystickButton(leftDriver, 1);

	public OI() {
		new JoystickButton(rightDriver, 1)
				.whileHeld(new JoystickArcadeDrive(this::getRightStraight, this::getLeftTurn));
		slowerButton.whenPressed(new SetDrivetrainMaximumSpeed(Constants.HIGH_MAX_SPEED));
		slowerButton.whenPressed(new SetDrivetrainMaximumSpeed(Constants.LOW_MAX_SPEED));
		// new JoystickButton(rightDriver, 7).whenPressed(new FrontStream());
		// new JoystickButton(rightDriver, 8).whenPressed(new RearStream());
		// new JoystickButton(rightDriver, 9).whenPressed(new StopCameras());
		new JoystickButton(rightNavigator, 1).whileHeld(new ExpandTriz());
		new JoystickButton(rightNavigator, 3).whileHeld(new RetractTriz());
		new JoystickButton(rightNavigator, 0).whileHeld(new ExpandFolder());
		new JoystickButton(rightNavigator, 2).whileHeld(new RetractFolder());
		new JoystickButton(rightNavigator, 5).whenPressed(new RollOut());
		new JoystickButton(rightNavigator, 6).whenPressed(new ScoreGoal(Goal.HIGH));
		new JoystickButton(rightNavigator, 4).whenPressed(new ShootByVoltage(Constants.SHOOTING_LOW_VOLTAGE));
	}

	private double adjustInput(double input) {
		return input * Math.abs(input);
	}

	public double getLeftStraight() {
		return adjustInput(-leftDriver.getY());
	}

	public double getLeftTurn() {
		return adjustInput(leftDriver.getX());
	}

	public double getRightStraight() {
		return adjustInput(-rightDriver.getY());
	}

	public double getRightTurn() {
		return adjustInput(rightDriver.getX());
	}

	public double getNavigatorStraight() {
		return -rightNavigator.getY();
	}

}
