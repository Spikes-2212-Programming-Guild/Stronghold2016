package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.Defense;
import com.spikes2212.robot2016.Field.Goal;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.drivetrain.PIDTurnDriveByAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ScoreGoalFromLocation1 extends CommandGroup {

	public static final double ROTATE_ANGLE = 180;

	public static final double FIRST_DISTANCE = 0; // FIXME
	public static final double ANGLE = 0; // FIXME
	public static final double SECOND_DISTANCE = 0; // FIXME
	public static final double TIMEOUT = 0.5; // FIXME

	public ScoreGoalFromLocation1(Defense defense, Goal goal) {
		if (defense == Defense.PORTCULLIS) {
			addSequential(new PIDTurnDriveByAngle(ROTATE_ANGLE));
		}
		addSequential(new PIDStraightDriveByDistance(FIRST_DISTANCE));
		addSequential(new WaitCommand(TIMEOUT));
		addSequential(new PIDTurnDriveByAngle(ANGLE));
		addSequential(new WaitCommand(TIMEOUT));
		addSequential(new PIDStraightDriveByDistance(SECOND_DISTANCE));
		addSequential(new ScoreGoal(goal));
	}

}
