package com.spikes2212.robot2016.commands.picker;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RollBallInAndMore extends CommandGroup {
	public final double TIME_AFTER_PICKING = 0.2;

	public RollBallInAndMore() {
		addSequential(new RollBoulderIn());
		addSequential(new RollIn(0.2));
	}
}
