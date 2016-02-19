package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.Direction;
import com.spikes2212.robot2016.commands.ExpandAll;
import com.spikes2212.robot2016.commands.drivetrain.PIDStay;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.drivetrain.PIDTurnDriveByAngleush;
import com.spikes2212.robot2016.commands.drivetrain.SetDrivetrainMaximumSpeed;
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

	public CrossPortcullis(Direction direction) {
		if (direction == Direction.FORWARD) {
			addSequential(new SetDrivetrainMaximumSpeed(0.6));
			addSequential(new ExpandAll());
			addSequential(new MoveTrizUp(), 0.2);
			addSequential(new PIDStraightDriveByDistance(1.5));
			addSequential(new SetDrivetrainMaximumSpeed(0.35));
			addSequential(new MoveTrizDown());
			addSequential(new PIDStraightDriveByDistance(1), 3);
			addParallel(new PIDStay());
			addSequential(new MoveTrizUp(), 1);
			// addSequential(new
			// PIDStraightDriveByDistance(FORWARD_AFTER_LIFTING_DISTANCE));
			addSequential(new SetDrivetrainMaximumSpeed(0.6));
			addSequential(new PIDStraightDriveByDistance(1.8));

		} else {
			addSequential(new MoveTrizDown());
			addSequential(new PIDTurnDriveByAngleush(ROTATE_ANGLE));
			addSequential(new PIDStraightDriveByDistance(BACKWARD_DISTANCE));
			addSequential(new MoveTrizUp());
			addSequential(new PIDStraightDriveByDistance(BACKWARD_AFTER_LIFTING_DISTANCE));
		}
	}
}
