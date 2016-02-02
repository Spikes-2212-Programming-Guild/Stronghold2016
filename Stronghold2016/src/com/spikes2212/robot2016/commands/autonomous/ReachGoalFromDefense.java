package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.Defense;
import com.spikes2212.robot2016.Field.DefenseLocation;
import com.spikes2212.robot2016.Field.Goal;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ReachGoalFromDefense extends CommandGroup {

	public ReachGoalFromDefense(Defense defense, DefenseLocation location, Goal goal) {
		switch (location) {
		case k1:
			addSequential(new ReachGoalFromDefense1(defense, goal));
			break;
		case k2:
			addSequential(new ReachGoalFromDefense2(defense, goal));
			break;
		case k3:
			addSequential(new ReachGoalFromDefense3(defense, goal));
			break;
		case k4:
			addSequential(new ReachGoalFromDefense4(defense, goal));
			break;
		case k5:
			addSequential(new ReachGoalFromDefense5(defense, goal));
			break;
		default:
			break;
		}
	}

}
