package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.commands.ExpandAll;
import com.spikes2212.robot2016.commands.triz.MoveTrizUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class InitializeLowBar extends CommandGroup {

	public InitializeLowBar() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		addSequential(new ExpandAll());
		addSequential(new MoveTrizUp(), 0.2);
	}
}