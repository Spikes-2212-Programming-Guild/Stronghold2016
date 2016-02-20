package com.spikes2212.robot2016.commands.picker;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RollBoulderInAndMore extends CommandGroup {
	public static final double TIME_AFTER_PICKING = 0.2;

	public RollBoulderInAndMore() {
		addSequential(new RollBoulderIn());
		addSequential(new RollIn(), TIME_AFTER_PICKING);
	}
}
