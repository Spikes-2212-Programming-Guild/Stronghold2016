package com.spikes2212.robot2016.commands.autonomous;

import static com.spikes2212.robot2016.Constants.METER;

import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CrossLowBar extends CommandGroup {

	public static final double LEFT_DISTANCE = 3.2 * METER;
	public static final double RIGHT_DISTANCE = 2.65 * METER;
	

	public CrossLowBar() {
		addParallel(new MoveTrizDownish());
		addSequential(new WaitCommand(2.5));
		addSequential(new PIDStraightDriveByDistance(LEFT_DISTANCE, RIGHT_DISTANCE, 0.4), 10);
	}
}
