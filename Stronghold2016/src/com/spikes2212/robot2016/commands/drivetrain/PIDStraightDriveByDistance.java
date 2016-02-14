package com.spikes2212.robot2016.commands.drivetrain;

import com.spikes2212.robot2016.commands.drivetrain.fixed.FixedPIDStraightDriveDistanceWithTwoEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PIDStraightDriveByDistance extends CommandGroup {

	public PIDStraightDriveByDistance(double distance) {
		addSequential(new FixedPIDStraightDriveDistanceWithTwoEncoders(distance));
	}

}
