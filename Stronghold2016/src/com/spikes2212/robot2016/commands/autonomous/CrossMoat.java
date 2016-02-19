package com.spikes2212.robot2016.commands.autonomous;

import static com.spikes2212.robot2016.Constants.METER;

import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.picker.RollOut;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CrossMoat extends CommandGroup {

	public static final double FIRST_DISTANCE = 3.2 * METER;

	public CrossMoat() {
		addParallel(new MoveTrizUpish());
		addSequential(new WaitCommand(1.5));
		addSequential(new PIDStraightDriveByDistance(FIRST_DISTANCE, 0.5), 2.5);
		addSequential(new WaitCommand(1));
		addSequential(new RollOut(), 1);
	}
}
