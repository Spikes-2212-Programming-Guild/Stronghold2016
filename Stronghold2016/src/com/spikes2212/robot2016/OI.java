package com.spikes2212.robot2016;

import com.spikes2212.robot2016.commands.drivetrain.JoystickForwardDrive;
import com.spikes2212.robot2016.commands.drivetrain.JoystickTurnDrive;
import com.spikes2212.robot2016.commands.roller.picker.RollBallIn;
import com.spikes2212.robot2016.commands.roller.picker.RollOut;
import com.spikes2212.robot2016.commands.triz.JoystickMoveTriz;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public final Joystick driver = new Joystick(0);
	public final Joystick rightNavigator = new Joystick(2);
	public final Joystick leftNavigator = new Joystick(1);

	public OI() {
		new JoystickButton(driver, 1).toggleWhenPressed(new JoystickForwardDrive(() -> -driver.getY()));
		new JoystickButton(driver, 3).toggleWhenPressed(new JoystickTurnDrive(() -> driver.getTwist()));
		new JoystickButton(rightNavigator, 6).whileHeld(new JoystickMoveTriz(() -> -rightNavigator.getY()));
		new JoystickButton(rightNavigator, 1).toggleWhenPressed(new RollBallIn());
		new JoystickButton(rightNavigator, 3).toggleWhenPressed(new RollOut());
	}

}
