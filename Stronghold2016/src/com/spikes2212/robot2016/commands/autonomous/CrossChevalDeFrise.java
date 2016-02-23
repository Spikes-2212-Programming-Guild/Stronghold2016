package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.commands.drivetrain.PIDStay;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.drivetrain.SetDrivetrainMaximumSpeed;
import com.spikes2212.robot2016.commands.folder.MoveFolderDown;
import com.spikes2212.robot2016.commands.triz.MoveTrizDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author alognruny
 *
 *         WARNING! in order to use this command correctly, the robot MUST be
 *         placed with the triz facing the outerworks.
 *
 */
public class CrossChevalDeFrise extends CommandGroup {

	public static final double ROTATE_ANGLE = 180;
	public static final double FORWARD_DISTANCE = 1.8;
	public static final double FORWARD_AFTER_LIFTING_DISTANCE = 0;
	public static final double BACKWARD_DISTANCE = 0;
	public static final double BACKWARD_AFTER_LIFTING_DISTANCE = 0;

	public CrossChevalDeFrise() {
		addSequential(new SetDrivetrainMaximumSpeed(Constants.HIGH_MAX_SPEED));
		addParallel(new MoveFolderDown(), 0.5);
		addSequential(new PIDStraightDriveByDistance(1.4, 1.4 * 2.8 / 3.2, Constants.HIGH_MAX_SPEED), 2.5);
		addParallel(new PIDStay(), 1.2);
		addSequential(new MoveTrizDown(Constants.TRIZ_DOWN_SPEED), 2.25);
		addSequential(new PIDStraightDriveByDistance(2.5, 2.5 * 2.8 / 3.2, Constants.HIGH_MAX_SPEED), 3.3);
		addSequential(new PIDStraightDriveByDistance(0.5, Constants.LOW_MAX_SPEED));

		// addSequential(new
		// SetDrivetrainMaximumSpeed(Constants.LOW_MAX_SPEED));
		// addSequential(new PIDStraightDriveByDistance(1));
		// addSequential(new)
		// SetDrivetrainMaximumSpeed(Constants.LOW_MAX_SPEED));
		// addSequential(new PIDStraightDriveByDistance(1));
		// addSequential(new
		// PIDStraightDriveByDistance(FORWARD_AFTER_LIFTING_DISTANCE));
		// addSequential(new SetDrivetrainMaximumSpeed(0.6));
		// addSequential(new PIDStraightDriveByDistance(1.8));

	}
}
