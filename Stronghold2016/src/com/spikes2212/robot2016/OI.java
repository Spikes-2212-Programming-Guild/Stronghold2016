package com.spikes2212.robot2016;

import com.spikes2212.robot2016.commands.camera.StopCameras;
import com.spikes2212.robot2016.commands.camera.UseFrontImage;
import com.spikes2212.robot2016.commands.camera.UseRearImage;
import com.spikes2212.robot2016.commands.drivetrain.JoystickForwardDrive;
import com.spikes2212.robot2016.commands.drivetrain.JoystickTurnDrive;
import com.spikes2212.robot2016.commands.folder.MoveFolderToShoot;
import com.spikes2212.robot2016.commands.picker.RollBallIn;
import com.spikes2212.robot2016.commands.picker.RollOut;
import com.spikes2212.robot2016.commands.shooter.ShootByVoltage;
import com.spikes2212.robot2016.commands.triz.JoystickMoveTriz;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public final Joystick leftDriver = new Joystick(0);
	public final Joystick rightDriver = new Joystick(1);
	public final Joystick rightNavigator = new Joystick(2);

	public OI() {
//		new JoystickButton(rightDriver, 1).toggleWhenPressed(new JoystickForwardDrive(() -> -leftDriver.getY()));
//		new JoystickButton(rightDriver, 3).toggleWhenPressed(new JoystickTurnDrive(leftDriver::getTwist));
		new JoystickButton(rightDriver, 7)
				.whenPressed(new UseFrontImage(image -> CameraServer.getInstance().setImage(image)));
		new JoystickButton(rightDriver, 8)
				.whenPressed(new UseRearImage(image -> CameraServer.getInstance().setImage(image)));
		new JoystickButton(rightDriver, 9).whenPressed(new StopCameras());
//		new JoystickButton(rightNavigator, 6).whileHeld(new JoystickMoveTriz(() -> -rightNavigator.getY()));
//		new JoystickButton(rightNavigator, 1).toggleWhenPressed(new RollBallIn());
//		new JoystickButton(rightNavigator, 3).toggleWhenPressed(new RollOut());
//		new JoystickButton(rightNavigator, 4).whenPressed(new ShootByVoltage(Constants.SHOOTING_VOLTAGE));
//		new JoystickButton(rightNavigator, 5).whenPressed(new MoveFolderToShoot());
	}

}
