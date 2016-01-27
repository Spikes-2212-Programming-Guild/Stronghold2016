package com.spikes2212.robot2016.commands.advanced;

import com.spikes2212.robot2016.commands.advanced.Cross.Defense;
import com.spikes2212.robot2016.commands.advanced.Cross.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CrossAndReturn extends CommandGroup {

	public static final double RETURN_TIMEOUT = 0;

	public CrossAndReturn(Defense defense) {
		addSequential(new Cross(defense, Direction.FORWARD));
		addSequential(new WaitCommand(RETURN_TIMEOUT));
		addSequential(new Cross(defense, Direction.BACKWARD));
	}

}