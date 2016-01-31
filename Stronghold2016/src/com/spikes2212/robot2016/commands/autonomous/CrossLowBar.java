package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.Direction;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.folder.MoveFolderUp;
import com.spikes2212.robot2016.commands.triz.MoveTrizDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossLowBar extends CommandGroup {

	public static final double DISTANCE = 0;

	public CrossLowBar(Direction direction) {
		// TODO: Make sure that MoveTrizDown is longer than ExpandRoller
		addParallel(new MoveFolderUp());
		addSequential(new MoveTrizDown());
		addSequential(new PIDStraightDriveByDistance(direction.getSpeedDirection() * DISTANCE));
	}
}