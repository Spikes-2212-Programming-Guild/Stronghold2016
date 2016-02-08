package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Calibrate;
import com.spikes2212.robot2016.Field.Direction;
import com.spikes2212.robot2016.commands.drivetrain.StraightDriveBySpeedAndTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossRamparts extends CommandGroup {

	@Calibrate(desc = "Choose a fitting value", unit = "second")
	public static final double TIME = 0;
	@Calibrate(desc = "Using SmartDashboard, choose a fitting value", unit = "pwm", min = 0, max = 1)
	public static final double SPEED = 0;

	public CrossRamparts(Direction direction) {
		addSequential(new StraightDriveBySpeedAndTime(direction.getSpeedDirection() * SPEED, TIME));
	}
}
