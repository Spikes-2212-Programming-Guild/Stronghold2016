package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.Defense;
import com.spikes2212.robot2016.Field.DefenseLocation;
import com.spikes2212.robot2016.Field.Goal;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreGoalFromLocation extends CommandGroup {

	public ScoreGoalFromLocation(Defense defense, DefenseLocation location, Goal goal) {
		switch (location) {
		case k1:
			addSequential(new ScoreGoalFromLocation1(defense, goal));
			break;
		case k2:
			addSequential(new ScoreGoalFromLocation2(defense, goal));
			break;
		case k3:
			addSequential(new ScoreGoalFromLocation3(defense, goal));
			break;
		case k4:
			addSequential(new ScoreGoalFromLocation4(defense, goal));
			break;
		case k5:
			addSequential(new ScoreGoalFromLocation5(defense, goal));
			break;
		default:
			break;
		}
	}

}
