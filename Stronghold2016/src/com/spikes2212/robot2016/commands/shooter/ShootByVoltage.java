package com.spikes2212.robot2016.commands.shooter;

import com.spikes2212.robot2016.commands.picker.RollIn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootByVoltage extends CommandGroup {

	public static final double ACCELERATION_TIME = 0;
	public static final double ROLL_IN_TIME = 0;

	public ShootByVoltage(double voltage) {
		addSequential(new RotateShooterByVoltageAndTime(voltage, ACCELERATION_TIME));
		addSequential(new RollIn(ROLL_IN_TIME));
	}

}
