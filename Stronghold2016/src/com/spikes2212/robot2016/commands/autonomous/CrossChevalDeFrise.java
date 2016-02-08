package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Calibrate;
import com.spikes2212.robot2016.Field.Direction;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.triz.MoveTrizDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossChevalDeFrise extends CommandGroup {

	@Calibrate(desc = "Using the SmartDashboard, choose a fitting value", unit = "meter")
	public static final double DISTANCE = 0;

	public CrossChevalDeFrise(Direction direction) {
		addSequential(new MoveTrizDown());
		addSequential(new PIDStraightDriveByDistance(direction.getSpeedDirection() * DISTANCE));
	}
}
