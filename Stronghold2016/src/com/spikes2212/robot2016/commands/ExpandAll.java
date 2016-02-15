package com.spikes2212.robot2016.commands;

import com.spikes2212.robot2016.commands.folder.ExpandFolder;
import com.spikes2212.robot2016.commands.triz.ExpandTriz;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ExpandAll extends CommandGroup {

	public ExpandAll() {
		addParallel(new ExpandTriz());
		addParallel(new ExpandFolder());
	}

}
