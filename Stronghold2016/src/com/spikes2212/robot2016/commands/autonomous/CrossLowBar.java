package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.Direction;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossLowBar extends CommandGroup {

	public static final double FIRST_DISTANCE = 1.52;
	public static final double SECOND_DISTANCE = 2.5;

	public CrossLowBar(Direction direction) {
		addSequential(new PIDStraightDriveByDistance(direction.getSpeedDirection() * FIRST_DISTANCE));
		addSequential(new PIDStraightDriveByDistance(direction.getSpeedDirection() * SECOND_DISTANCE));
	}
}
