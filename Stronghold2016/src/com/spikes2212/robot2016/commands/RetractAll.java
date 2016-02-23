package com.spikes2212.robot2016.commands;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.commands.folder.MoveFolderUp;
import com.spikes2212.robot2016.commands.triz.MoveTrizUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RetractAll extends CommandGroup {

	public RetractAll() {
		addParallel(new MoveTrizUp(Constants.TRIZ_UP_SPEED));
		addParallel(new MoveFolderUp());
	}

}
