package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.Defense;
import com.spikes2212.robot2016.Field.DefenseLocation;
import com.spikes2212.robot2016.Field.Direction;
import com.spikes2212.robot2016.Field.Goal;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossAndScoreGoal extends CommandGroup {

	public static /*final*/ double WAIT_TIME = 0;

	public CrossAndScoreGoal(Defense defense, DefenseLocation location, Goal goal) {
		addSequential(new Cross(defense, Direction.FORWARD));
		addSequential(new ScoreGoalFromLocation(defense, location, goal));
		addSequential(new ScoreGoal(goal));
	}

}
