package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.commands.drivetrain.StraightDriveBySpeedAndTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossRockWall extends CommandGroup {

	public static final double SPEED = 0;

	public CrossRockWall() {
		addSequential(new StraightDriveBySpeedAndTime(SPEED, 2.5));
	}
}
