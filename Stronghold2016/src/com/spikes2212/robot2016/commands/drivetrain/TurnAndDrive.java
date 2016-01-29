package com.spikes2212.robot2016.commands.drivetrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class TurnAndDrive extends CommandGroup {

	public static final double WAIT_TIME = 0.5;

	public TurnAndDrive(double angle, double distance) {
		addSequential(new PIDTurnDriveByAngle(angle));
		addSequential(new WaitCommand(WAIT_TIME));
		addSequential(new PIDStraightDriveByDistance(distance));
	}

}
