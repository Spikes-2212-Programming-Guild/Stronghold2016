package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ReachDefense extends CommandGroup {

	public static final double DISTANCE = 0;

	public ReachDefense() {
		addSequential(new PIDStraightDriveByDistance(DISTANCE));
	}

}
