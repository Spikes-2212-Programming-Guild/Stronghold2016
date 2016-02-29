package com.spikes2212.robot2016.commands;

import com.spikes2212.robot2016.commands.picker.WaitAndMoveToShooter;
import com.spikes2212.robot2016.commands.shooter.RotateShooterByVoltage;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Shoot extends CommandGroup {

	public Shoot(double voltage) {
		addParallel(new RotateShooterByVoltage(voltage));
		addSequential(new WaitAndMoveToShooter());
	}
}