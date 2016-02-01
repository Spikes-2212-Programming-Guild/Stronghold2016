package com.spikes2212.robot2016.commands;

import com.spikes2212.robot2016.commands.folder.MoveFolderUp;
import com.spikes2212.robot2016.commands.triz.MoveTrizUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveAllUp extends CommandGroup {

	public MoveAllUp() {
		addParallel(new MoveTrizUp());
		addParallel(new MoveFolderUp());
	}

}
