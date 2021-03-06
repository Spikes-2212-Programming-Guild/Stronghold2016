package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.commands.ExpandAll;
import com.spikes2212.robot2016.commands.folder.MoveFolderUp;
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
		addSequential(new MoveTrizUp(Constants.TRIZ_UP_SPEED), 0.2);
		addSequential(new MoveFolderUp(), 0.1);
	}
}