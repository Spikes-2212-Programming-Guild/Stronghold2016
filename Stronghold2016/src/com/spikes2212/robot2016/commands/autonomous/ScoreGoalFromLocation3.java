package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.Defense;
import com.spikes2212.robot2016.Field.Goal;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.drivetrain.PIDTurnDriveByAngleush;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ScoreGoalFromLocation3 extends CommandGroup {

	public static final double FIRST_DISTANCE = 0;
	public static final double ANGLE = 0;
	public static final double SECOND_DISTANCE = 0;
	public static final double TIMEOUT = 0.5;
	public static final double ROTATE_ANGLE = 180;

	public ScoreGoalFromLocation3(Defense defense, Goal goal) {
		if (defense == Defense.PORTCULLIS) {
			addSequential(new PIDTurnDriveByAngleush(ROTATE_ANGLE));
		}
		addSequential(new PIDStraightDriveByDistance(FIRST_DISTANCE));
		addSequential(new WaitCommand(TIMEOUT));
		addSequential(new PIDTurnDriveByAngleush(ANGLE));
		addSequential(new WaitCommand(TIMEOUT));
		addSequential(new PIDStraightDriveByDistance(SECOND_DISTANCE));
		addSequential(new ScoreGoal(goal));
	}
}
