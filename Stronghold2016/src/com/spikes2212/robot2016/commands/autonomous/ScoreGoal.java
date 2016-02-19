package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.Field.Goal;
import com.spikes2212.robot2016.commands.Shoot;
import com.spikes2212.robot2016.commands.picker.RollOut;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreGoal extends CommandGroup {

	public ScoreGoal(Goal goal) {
		addSequential(new MoveFolderToGoal(goal));
		switch (goal) {
		case LOW:
			addSequential(new RollOut());
			break;
		case HIGH:
			addSequential(new Shoot(Constants.SHOOTING_HIGH_VOLTAGE));
			break;
		}
	}
}
