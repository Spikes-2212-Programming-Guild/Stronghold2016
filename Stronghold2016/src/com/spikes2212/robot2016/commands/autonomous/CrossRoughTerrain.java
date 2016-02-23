
package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.commands.drivetrain.StraightDriveBySpeedAndTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossRoughTerrain extends CommandGroup {

	public static final double SPEED = 0.5;

	public CrossRoughTerrain() {
		addSequential(new StraightDriveBySpeedAndTime(SPEED, 3.5));
	}
}
