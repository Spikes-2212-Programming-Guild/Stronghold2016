package com.spikes2212.robot2016;

import com.spikes2212.robot2016.commands.drivetrain.JoystickForwardDrive;
import com.spikes2212.robot2016.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
/**
 * 
 * @author Itamar
 *
 */
public class OI {
	public final Joystick rightDriver = new Joystick(0);
	public final Joystick leftDriver = new Joystick(0);
	public final Joystick navigator = new Joystick(0);
	public final Button forwardButton = new JoystickButton(rightDriver, 1);

	public OI() {
		forwardButton.whileHeld(new JoystickForwardDrive());
	}

}
