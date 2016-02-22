package com.spikes2212.robot2016.commands;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.commands.folder.MoveFolderDown;
import com.spikes2212.robot2016.commands.triz.MoveTrizDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ExpandAll extends CommandGroup {

	public ExpandAll() {
		addParallel(new MoveTrizDown(Constants.TRIZ_DOWN_SPEED));
		addParallel(new MoveFolderDown());
	}

}
