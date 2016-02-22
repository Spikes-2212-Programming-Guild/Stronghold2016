package com.spikes2212.robot2016.commands.autonomous;

import static com.spikes2212.robot2016.Constants.METER;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CrossLowBar extends CommandGroup {

	public static final double FIRST_DISTANCE = 3.2 * METER;

	public CrossLowBar() {
		addParallel(new MoveTrizUpish());
		addSequential(new WaitCommand(1.5));
		// addSequential(new PIDStraightDriveByDistance(FIRST_DISTANCE,
		// Constants.CROSS_LOW_BAR_MAX_SPEED), 2.5);
	}
}
