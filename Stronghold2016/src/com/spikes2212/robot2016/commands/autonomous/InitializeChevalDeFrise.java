package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.commands.RetractAll;
import com.spikes2212.robot2016.commands.folder.MoveFolderDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class InitializeChevalDeFrise extends CommandGroup {

	public InitializeChevalDeFrise() {
		addSequential(new RetractAll());
		addParallel(new MoveFolderDown(), 0.5);
	}
}