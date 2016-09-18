package com.spikes2212.robot2016;

import com.spikes2212.robot2016.commands.NextAuto;
import com.spikes2212.robot2016.commands.PreviousAuto;
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
import com.spikes2212.robot2016.commands.triz.MoveTrizDown;
import com.spikes2212.robot2016.commands.triz.MoveTrizUp;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
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

	JoystickButton rightDriverTrigger;

	JoystickButton rightDriver2;
	JoystickButton rightDriver3;
	JoystickButton rightDriver6;
	JoystickButton rightDriver7;
	JoystickButton rightDriver8;
	JoystickButton rightDriver9;

	public OI() {

		rightDriver2 = new JoystickButton(rightDriver, 2);
		rightDriver3 = new JoystickButton(rightDriver, 3);
		rightDriver6 = new JoystickButton(rightDriver, 6);
		rightDriver7 = new JoystickButton(rightDriver, 7);
		rightDriver8 = new JoystickButton(rightDriver, 8);
		rightDriver9 = new JoystickButton(rightDriver, 9);

		rightDriver2.whileHeld(new MoveTrizDown(0.5));
		rightDriver3.whileHeld(new MoveTrizUp(0.5));

		rightDriver6.whileHeld(new MoveFolderDown());
		rightDriver7.whileHeld(new MoveFolderUp());

		rightDriver8.whileHeld(new RollBoulderInAndMore());
		rightDriver9.whileHeld(new RotateShooterByVoltage(Constants.SHOOTING_HIGH_VOLTAGE));

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

	/**
	 * 
	 * @return The X-axis value of the driver's left joystick
	 */

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


}
