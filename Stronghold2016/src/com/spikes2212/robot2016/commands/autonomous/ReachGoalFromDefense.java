package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.DefenseLocation;
import com.spikes2212.robot2016.Field.Goal;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ReachGoalFromDefense extends CommandGroup {

	public ReachGoalFromDefense(DefenseLocation location, Goal goal) {
		switch (location) {
		case k1:
			addSequential(new ReachGoalFromDefense1(goal));
			break;
		case k2:
			addSequential(new ReachGoalFromDefense2(goal));
			break;
		case k3:
			addSequential(new ReachGoalFromDefense3(goal));
			break;
		case k4:
			addSequential(new ReachGoalFromDefense4(goal));
			break;
		case k5:
			addSequential(new ReachGoalFromDefense5(goal));
			break;
		default:
			break;
		}
	}

}
