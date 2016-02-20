package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.Defense;
import com.spikes2212.robot2016.commands.drivetrain.PIDTurnDriveByAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CrossAndReturn extends CommandGroup {

	public static final double TURN_ANGLE = 180;
	public static final double TURN_TIMEOUT = 2.5;

	public static final double WAIT_TIME = 1;

	public CrossAndReturn(Defense defense) {
		addSequential(new Cross(defense));
		addSequential(new WaitCommand(WAIT_TIME));
		addSequential(new PIDTurnDriveByAngle(TURN_ANGLE), TURN_TIMEOUT);
		addSequential(new WaitCommand(WAIT_TIME));
		addSequential(new Cross(defense));
	}

}
