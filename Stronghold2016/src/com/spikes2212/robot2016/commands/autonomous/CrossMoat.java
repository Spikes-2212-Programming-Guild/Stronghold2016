package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Calibrate;
import com.spikes2212.robot2016.Field.Direction;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossMoat extends CommandGroup {

	@Calibrate(desc = "Using the SmartDashboard, choose a fitting value", unit = "meter")
	public static final double DISTANCE = 0;

	public CrossMoat(Direction direction) {
		addSequential(new PIDStraightDriveByDistance(direction.getSpeedDirection() * DISTANCE));
	}
}
