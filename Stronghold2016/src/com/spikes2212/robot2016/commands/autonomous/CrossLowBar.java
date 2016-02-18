package com.spikes2212.robot2016.commands.autonomous;

import static com.spikes2212.robot2016.Constants.FEET;
import static com.spikes2212.robot2016.Constants.INCH;

import com.spikes2212.robot2016.Field.Direction;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossLowBar extends CommandGroup {

	public static final double FIRST_DISTANCE = 7 * FEET + 2 * INCH;

	public CrossLowBar(Direction direction) {
		addSequential(new PIDStraightDriveByDistance(direction.getSpeedDirection() * FIRST_DISTANCE));
	}
}
