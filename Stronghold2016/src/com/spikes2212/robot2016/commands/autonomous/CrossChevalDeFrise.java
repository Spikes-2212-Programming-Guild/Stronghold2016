package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.Direction;
import com.spikes2212.robot2016.commands.drivetrain.DriveUntilBump;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.triz.MoveTrizDown;
import com.spikes2212.robot2016.commands.triz.MoveTrizUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossChevalDeFrise extends CommandGroup {

	public static final double DISTANCE = 1;
	public static final double SPEED = 0.3;

	public CrossChevalDeFrise(/*Direction direction*/) {
		addSequential(new MoveTrizDown());
		addSequential(new DriveUntilBump(/*direction.getSpeedDirection()**/SPEED ));
		addParallel(new MoveTrizUp());
		addSequential(new PIDStraightDriveByDistance(DISTANCE));
	}
}
