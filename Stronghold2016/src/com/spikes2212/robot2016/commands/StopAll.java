package com.spikes2212.robot2016.commands;

import com.spikes2212.robot2016.commands.folder.StopFolder;
import com.spikes2212.robot2016.commands.picker.StopRoll;
import com.spikes2212.robot2016.commands.shooter.StopShooter;
import com.spikes2212.robot2016.commands.triz.StopTriz;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StopAll extends CommandGroup {

	public StopAll() {
		addParallel(new StopTriz());
		addParallel(new StopFolder());
		addParallel(new StopShooter());
		addParallel(new StopRoll());
	}

}
