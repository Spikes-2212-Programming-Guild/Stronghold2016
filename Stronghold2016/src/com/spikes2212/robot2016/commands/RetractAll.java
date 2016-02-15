package com.spikes2212.robot2016.commands;

import com.spikes2212.robot2016.commands.folder.RetractFolder;
import com.spikes2212.robot2016.commands.triz.RetractTriz;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RetractAll extends CommandGroup {

	public RetractAll() {
		addParallel(new RetractTriz());
		addParallel(new RetractFolder());
	}

}
