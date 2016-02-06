package com.spikes2212.robot2016.commands;

import com.spikes2212.robot2016.commands.folder.MoveFolderDown;
import com.spikes2212.robot2016.commands.triz.MoveTrizDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveAllDown extends CommandGroup {

	public MoveAllDown() {
		addParallel(new MoveTrizDown());
		addParallel(new MoveFolderDown());
	}

}
