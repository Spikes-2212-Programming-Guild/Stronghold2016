package com.spikes2212.robot2016.commands.picker;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RollInALittle extends CommandGroup {

	public static final double TIMEOUT = 2;

	public RollInALittle() {
		addSequential(new RollIn(TIMEOUT));
	}

}
