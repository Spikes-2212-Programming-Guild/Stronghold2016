package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.Direction;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.triz.ExpandTriz;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossChevalDeFrise extends CommandGroup {

	public static final double DISTANCE = 0;

	public CrossChevalDeFrise(Direction direction) {
		addSequential(new ExpandTriz());
		addSequential(new PIDStraightDriveByDistance(direction.getSpeedDirection() * DISTANCE));
	}
}
