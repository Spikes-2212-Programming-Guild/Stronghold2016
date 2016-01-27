package com.spikes2212.robot2016.commands.advanced;

import com.spikes2212.robot2016.Permanents;
import com.spikes2212.robot2016.commands.advanced.Cross.Direction;
import com.spikes2212.robot2016.commands.drivetrain.StraightDriveByDistance;
import com.spikes2212.robot2016.commands.drivetrain.StraightDriveToPortcullis;
import com.spikes2212.robot2016.commands.drivetrain.TurnDriveByAngle;
import com.spikes2212.robot2016.commands.triz.LiftPortcullis;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossPortcullis extends CommandGroup {

	public static final double ROTATE_ANGLE = 180;

	public CrossPortcullis(Direction direction) {
		if (direction == Direction.BACKWARD) {
			addSequential(new TurnDriveByAngle(ROTATE_ANGLE));
		}
		addSequential(new StraightDriveToPortcullis(Permanents.DRIVE_TO_PORTCULLIS_VELOCITY));
		addSequential(new LiftPortcullis(Permanents.LIFTING_PORTCULLIS_DISTANCE));
		addSequential(new StraightDriveByDistance(Permanents.AFTER_LIFTING_PORTCULLIS_DISTANCE));
	}
}
