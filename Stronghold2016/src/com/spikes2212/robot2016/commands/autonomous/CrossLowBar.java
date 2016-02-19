package com.spikes2212.robot2016.commands.autonomous;

import static com.spikes2212.robot2016.Constants.METER;

import com.spikes2212.robot2016.Field.Direction;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.drivetrain.PIDTurnDriveByAngle;
import com.spikes2212.robot2016.commands.drivetrain.SetDrivetrainMaximumSpeed;
import com.spikes2212.robot2016.commands.picker.RollOut;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CrossLowBar extends CommandGroup {

	public static final double FIRST_DISTANCE = 3.2 * METER;

	public CrossLowBar(Direction direction) {
		addParallel(new MoveTrizUpish());
		addSequential(new WaitCommand(1.5));
		addSequential(new SetDrivetrainMaximumSpeed(0.5));
		addSequential(new PIDStraightDriveByDistance(direction.getSpeedDirection() * FIRST_DISTANCE), 2.5);
		addSequential(new WaitCommand(1));
		addSequential(new PIDTurnDriveByAngle(180), 2.5);
		addSequential(new RollOut(), 1);
	}
}
