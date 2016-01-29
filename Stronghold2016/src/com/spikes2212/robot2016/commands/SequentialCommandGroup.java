package com.spikes2212.robot2016.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class SequentialCommandGroup extends CommandGroup {

	public SequentialCommandGroup(Command... commands) {
		for (Command command : commands) {
			addSequential(command);
		}
	}

}
