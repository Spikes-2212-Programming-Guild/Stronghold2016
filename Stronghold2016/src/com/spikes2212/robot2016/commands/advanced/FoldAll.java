package com.spikes2212.robot2016.commands.advanced;

import com.spikes2212.robot2016.commands.folder.MoveFolderDown;
import com.spikes2212.robot2016.commands.triz.MoveTrizDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FoldAll extends CommandGroup {

	public FoldAll() {
		addParallel(new MoveTrizDown());
		addParallel(new MoveFolderDown());
	}

}
