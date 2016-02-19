package com.spikes2212.robot2016.commands;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.commands.picker.MoveToShooter;
import com.spikes2212.robot2016.commands.shooter.RotateShooterByVoltage;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class Shoot extends CommandGroup {

	public Shoot(double voltage) {
		addParallel(new RotateShooterByVoltage(voltage));
		addSequential(new WaitCommand(Constants.TIME_GAP_FOR_SHOOTING));
		addParallel(new MoveToShooter(), Constants.ROLL_IN_TIME);
	}
}