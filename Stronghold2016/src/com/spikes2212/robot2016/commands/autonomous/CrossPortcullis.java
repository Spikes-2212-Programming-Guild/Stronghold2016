package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.commands.ExpandAll;
import com.spikes2212.robot2016.commands.drivetrain.PIDStay;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.triz.MoveTrizDown;
import com.spikes2212.robot2016.commands.triz.MoveTrizUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author alognruny
 *
 *         WARNING! in order to use this command correctly, the robot MUST be
 *         placed with the triz facing the outerworks.
 *
 */
public class CrossPortcullis extends CommandGroup {

	public static final double ROTATE_ANGLE = 180;
	public static final double FORWARD_DISTANCE = 1.8;
	public static final double FORWARD_AFTER_LIFTING_DISTANCE = 0;
	public static final double BACKWARD_DISTANCE = 0;
	public static final double BACKWARD_AFTER_LIFTING_DISTANCE = 0;

	public CrossPortcullis() {
		addSequential(new ExpandAll());
		addSequential(new MoveTrizUp(), 0.2);
		addSequential(new PIDStraightDriveByDistance(1.5, Constants.HIGH_MAX_SPEED));
		addSequential(new MoveTrizDown());
		addSequential(new PIDStraightDriveByDistance(1, Constants.MEDIUM_MAX_SPEED), 3);
		addParallel(new PIDStay());
		addSequential(new MoveTrizUp(), 1);
		// addSequential(new
		// PIDStraightDriveByDistance(FORWARD_AFTER_LIFTING_DISTANCE));
		addSequential(new PIDStraightDriveByDistance(1.8, Constants.HIGH_MAX_SPEED));

	}
}
