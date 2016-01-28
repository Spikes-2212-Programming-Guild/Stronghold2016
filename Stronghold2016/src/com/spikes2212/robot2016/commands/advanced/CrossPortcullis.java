package com.spikes2212.robot2016.commands.advanced;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.commands.advanced.Cross.Direction;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.drivetrain.StraightDriveToPortcullis;
import com.spikes2212.robot2016.commands.drivetrain.PIDTurnDriveByAngle;
import com.spikes2212.robot2016.commands.triz.PIDMoveTriz;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossPortcullis extends CommandGroup {

	public static final double ROTATE_ANGLE = 180;

	public CrossPortcullis(Direction direction) {
		if (direction == Direction.BACKWARD) {
			addSequential(new PIDTurnDriveByAngle(ROTATE_ANGLE));
		}
		addSequential(new StraightDriveToPortcullis(Constants.DRIVE_TO_PORTCULLIS_VELOCITY));
		addSequential(new PIDMoveTriz(Constants.LIFTING_PORTCULLIS_DISTANCE));
		addSequential(new PIDStraightDriveByDistance(Constants.AFTER_LIFTING_PORTCULLIS_DISTANCE));
	}
}
