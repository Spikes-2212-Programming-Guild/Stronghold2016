package com.spikes2212.robot2016.commands.triz;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import static com.spikes2212.robot2016.Robot.oi;
import static com.spikes2212.robot2016.Robot.triz;

import com.spikes2212.robot2016.consts.TrizConstants;

/**
 * @author Itamar
 */
public class JoystickMoveTriz extends Command {
	Joystick navigator = oi.navigator;

	public JoystickMoveTriz() {
		requires(triz);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!isFinished())
			if (navigator.getRawButton(TrizConstants.TRIZ_FOLD_BUTTON))
				triz.fold(TrizConstants.TRIZ_FOLD_SPEED);
			else
				triz.fold(TrizConstants.TRIZ_UNFOLD_SPEED);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !navigator.getRawButton(TrizConstants.TRIZ_FOLD_BUTTON);
	}

	// Called once after isFinished returns true
	protected void end() {
		triz.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
