package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.drivetrain.PIDTurnDriveByAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ReachTowerFromDefense3 extends CommandGroup {

	public static final double FIRST_DISTANCE = 0;
	public static final double ANGLE = 0;
	public static final double SECOND_DISTANCE = 0;
	public static final double TIMEOUT = 0.5;

	public ReachTowerFromDefense3() {
		addSequential(new PIDStraightDriveByDistance(FIRST_DISTANCE));
		addSequential(new WaitCommand(TIMEOUT));
		addSequential(new PIDTurnDriveByAngle(ANGLE));
		addSequential(new WaitCommand(TIMEOUT));
		addSequential(new PIDStraightDriveByDistance(FIRST_DISTANCE));
	}

}
