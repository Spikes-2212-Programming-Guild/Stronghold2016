package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.Goal;
import com.spikes2212.robot2016.commands.folder.MoveFolderDown;
import com.spikes2212.robot2016.commands.folder.MoveFolderToShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PrepareToGoal extends CommandGroup {

	public PrepareToGoal(Goal goal) {
		switch (goal) {
		case LOW:
			addSequential(new MoveFolderDown());
			break;
		case HIGH:
			addSequential(new MoveFolderToShoot());
			break;
		}
	}
}
