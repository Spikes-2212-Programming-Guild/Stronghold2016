package com.spikes2212.robot2016;

import com.spikes2212.robot2016.commands.StopAll;
import com.spikes2212.robot2016.commands.autonomous.InitializeChevalDeFrise;
import com.spikes2212.robot2016.commands.autonomous.InitializeLowBar;
import com.spikes2212.robot2016.commands.camera.FrontStream;
import com.spikes2212.robot2016.commands.camera.RearStream;
import com.spikes2212.robot2016.commands.camera.StopCameras;
import com.spikes2212.robot2016.commands.drivetrain.SetDrivetrainMaximumSpeed;
import com.spikes2212.robot2016.commands.drivetrain.TwoJoysticksDrive;
import com.spikes2212.robot2016.commands.folder.MoveFolderDown;
import com.spikes2212.robot2016.commands.folder.MoveFolderUp;
import com.spikes2212.robot2016.commands.picker.RollBoulderInAndMore;
import com.spikes2212.robot2016.commands.picker.RollOut;
import com.spikes2212.robot2016.commands.shooter.RotateShooterByVoltage;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * 
 * 
 * Dear driver,<br/>
 * 
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * You should never touch this class or any other class during competition. Even
 * though, it may be useful to know how to change things yourself without the
 * need to call a programmer. Therefore, a little documentation is included in
 * this class.
 * 
 * Please read this class <b>thoroughly</b> - it's not that hard.
 * 
 * The operations that the robot can do are called commands. Each command has a
 * separate class. For example, "MoveTrizDown" is a class in the package
 * .../commands/triz.
 * 
 * You can change when a command is started. Mainly, you have 3 options:
 * <ul>
 * <li>whenPressed - the command is started when you press a button</li>
 * <li>whenReleased - the command is started when you release a button</li>
 * <li>whileHeld - the command is started when you press a button, and is
 * canceled when you release it</li>
 * <li>toggleWhenPressed - the command is toggled when you press a button</li>
 * </ul>
 * 
 */
public class OI /* GEVALD */ {
	private final Joystick rightDriver = new Joystick(0);
	private final Joystick leftDriver = new Joystick(1);
	private final Joystick rightNavigator = new Joystick(2);

	JoystickButton rightDriverTrigger;
	JoystickButton leftDriverTrigger;

	JoystickButton rightDriver2;
	JoystickButton rightDriver3;
	JoystickButton rightDriver4;
	JoystickButton rightDriver5;

	JoystickButton navigatorBlue;
	JoystickButton navigatorGreen;
	JoystickButton navigatorYellow;
	JoystickButton navigatorRed;
	JoystickButton navigatorLB;
	JoystickButton navigatorRB;
	JoystickButton navigatorLT;
	JoystickButton navigatorRT;
	JoystickButton navigatorBack;
	JoystickButton navigatorStart;
	JoystickButton navigator11;

	public OI() {
		rightDriverTrigger = new JoystickButton(rightDriver, 1);
		leftDriverTrigger = new JoystickButton(leftDriver, 1);

		rightDriver2 = new JoystickButton(rightDriver, 2);
		rightDriver3 = new JoystickButton(rightDriver, 3);
		rightDriver4 = new JoystickButton(rightDriver, 4);
		rightDriver5 = new JoystickButton(rightDriver, 5);

		navigatorBlue = new JoystickButton(rightNavigator, 1);
		navigatorGreen = new JoystickButton(rightNavigator, 2);
		navigatorRed = new JoystickButton(rightNavigator, 3);
		navigatorYellow = new JoystickButton(rightNavigator, 4);

		navigatorRT = new JoystickButton(rightNavigator, 8);
		navigatorLT = new JoystickButton(rightNavigator, 7);
		navigatorRB = new JoystickButton(rightNavigator, 6);
		navigatorLB = new JoystickButton(rightNavigator, 5);

		navigatorBack = new JoystickButton(rightNavigator, 9);
		navigatorStart = new JoystickButton(rightNavigator, 10);
		navigator11 = new JoystickButton(rightNavigator, 11);

		rightDriverTrigger.whileHeld(new TwoJoysticksDrive(this::getLeftStraight, this::getRightStraight));
		// A low value between 0 and 1 such as 0.4
		leftDriverTrigger.whenPressed(new SetDrivetrainMaximumSpeed(Constants.LOW_MAX_SPEED));
		// A high value between 0 and 1 such as 0.8
		leftDriverTrigger.whenReleased(new SetDrivetrainMaximumSpeed(Constants.VERY_HIGH_MAX_SPEED));

		rightDriver5.whenPressed(new FrontStream());
		rightDriver4.whenPressed(new RearStream());
		rightDriver3.whenPressed(new StopCameras());

		navigatorYellow.whileHeld(new MoveFolderUp());
		navigatorGreen.whileHeld(new MoveFolderDown());

		navigatorRT.whileHeld(new RollBoulderInAndMore());
		navigatorRB.whileHeld(new RollOut());

		// A high voltage below 12 such as 11.8
		navigatorLT.whileHeld(new RotateShooterByVoltage(Constants.SHOOTING_HIGH_VOLTAGE));

		navigatorBack.whenPressed(new InitializeLowBar());
		navigatorStart.whenPressed(new InitializeChevalDeFrise());
		navigator11.whenPressed(new StopAll());

	}

	/**
	 * Increase the sensitivity by squaring the joystick's input.
	 * 
	 * @param input
	 *            The joystick's input
	 * @return A new scaled value
	 */
	private double adjustInput(double input) {
		return input * Math.abs(input);
	}

	/**
	 * 
	 * @return The Y-axis value of the driver's left joystick
	 */
	public double getLeftStraight() {
		return adjustInput(-leftDriver.getY());
	}

	/**
	 * 
	 * @return The X-axis value of the driver's left joystick
	 */
	public double getLeftTurn() {
		return adjustInput(-leftDriver.getX());
	}

	/**
	 * 
	 * @return The Y-axis value of the driver's right joystick
	 */
	public double getRightStraight() {
		return adjustInput(-rightDriver.getY());
	}

	/**
	 * Not used.
	 * 
	 * @return The X-axis value of the driver's right joystick
	 */
	public double getRightTurn() {
		return adjustInput(-rightDriver.getX());
	}

	/**
	 * Not used.
	 * 
	 * @return The X-axis value of the navigator's joystick
	 */

	public double getNavigatorStraight() {
		return -rightNavigator.getY();
	}

}
