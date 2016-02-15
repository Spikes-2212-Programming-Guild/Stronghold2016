package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.Goal;
import com.spikes2212.robot2016.commands.folder.ExpandFolder;
import com.spikes2212.robot2016.commands.folder.MoveFolderToShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveFolderToGoal extends CommandGroup {

	public MoveFolderToGoal(Goal goal) {
		switch (goal) {
		case LOW:
			addSequential(new ExpandFolder());
			break;
		case HIGH:
			addSequential(new MoveFolderToShoot());
			break;
		}
	}
}
