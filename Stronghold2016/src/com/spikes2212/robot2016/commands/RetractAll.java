package com.spikes2212.robot2016.commands;

import com.spikes2212.robot2016.commands.folder.MoveFolderDown;
import com.spikes2212.robot2016.commands.triz.MoveTrizUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RetractAll extends CommandGroup {

	public RetractAll() {
		addParallel(new MoveTrizUp());
		addParallel(new MoveFolderDown());
	}

}
