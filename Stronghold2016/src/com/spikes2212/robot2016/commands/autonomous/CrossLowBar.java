package com.spikes2212.robot2016.commands.autonomous;

import static com.spikes2212.robot2016.Constants.METER;

import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.drivetrain.StraightDriveBySpeedAndTime;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CrossLowBar extends CommandGroup {

	public static final double DISTANCE = 3.2 * METER;
	

	public CrossLowBar() {
		addParallel(new MoveTrizDownish());
		addSequential(new WaitCommand(2.5));
		addSequential(new StraightDriveBySpeedAndTime(0.4, 4));
	}
}
