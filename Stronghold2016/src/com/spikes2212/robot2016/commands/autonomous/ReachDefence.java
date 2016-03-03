
package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.commands.drivetrain.StraightDriveBySpeedAndTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ReachDefence extends CommandGroup {

	public static final double SPEED = 0.5;

	public ReachDefence() {
		addSequential(new StraightDriveBySpeedAndTime(SPEED, 1.7));
	}
}
