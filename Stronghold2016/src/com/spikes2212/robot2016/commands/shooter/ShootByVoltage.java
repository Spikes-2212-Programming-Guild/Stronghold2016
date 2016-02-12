package com.spikes2212.robot2016.commands.shooter;

import com.spikes2212.robot2016.commands.picker.RollIn;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootByVoltage extends CommandGroup {

	public static final double ACCELERATION_TIME = 5;
	public static final double WAIT_TIME_BEFORE_ROLLING = 3;
	public static final double ROLL_IN_TIME = 1;

	public ShootByVoltage(double voltage) {
		addParallel(new RotateShooterByVoltageAndTime(voltage, ACCELERATION_TIME));
		addSequential(new WaitCommand(WAIT_TIME_BEFORE_ROLLING));
		addSequential(new RollIn(ROLL_IN_TIME));
	}

}
