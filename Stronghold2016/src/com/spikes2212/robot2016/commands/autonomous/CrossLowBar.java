package com.spikes2212.robot2016.commands.autonomous;

import static com.spikes2212.robot2016.Constants.FEET;
import static com.spikes2212.robot2016.Constants.INCH;

import com.spikes2212.robot2016.Field.Direction;
import com.spikes2212.robot2016.commands.ExpandAll;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.drivetrain.SetDrivetrainMaximumSpeed;
import com.spikes2212.robot2016.commands.triz.MoveTrizUp;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CrossLowBar extends CommandGroup {

	public static final double FIRST_DISTANCE = 6 * FEET + 2 * INCH;
	public static final double SECOND_DISTANCE = 3 * FEET + 1 * INCH;

	public CrossLowBar(Direction direction) {
		addSequential(new SetDrivetrainMaximumSpeed(0.6));
		addSequential(new ExpandAll());
		addSequential(new MoveTrizUp(), 0.5);
		addSequential(new WaitCommand(1.5));
		addSequential(new PIDStraightDriveByDistance(direction.getSpeedDirection() * FIRST_DISTANCE), 2.5);
		addSequential(new PIDStraightDriveByDistance(direction.getSpeedDirection() * SECOND_DISTANCE));
	}
}
