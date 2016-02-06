package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.Field.Goal;
import com.spikes2212.robot2016.commands.picker.RollOut;
import com.spikes2212.robot2016.commands.shooter.Shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreGoal extends CommandGroup {

	public ScoreGoal(Goal goal) {
		switch (goal) {
		case LOW:
			addSequential(new RollOut());
			break;
		case HIGH:
			addSequential(new Shoot(Constants.SHOOTING_VOLTAGE));
			break;
		}
	}
}
