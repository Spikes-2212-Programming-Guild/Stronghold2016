package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.commands.folder.MoveFolderDown;
import com.spikes2212.robot2016.commands.triz.MoveTrizDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveTrizUpish extends CommandGroup {

	public MoveTrizUpish() {
		addParallel(new MoveFolderDown());
		addSequential(new MoveTrizDown(Constants.TRIZ_DOWN_SPEED), 2.15);
	}

}
