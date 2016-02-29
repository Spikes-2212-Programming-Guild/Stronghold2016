package com.spikes2212.robot2016.commands.picker;

import com.spikes2212.robot2016.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class WaitAndMoveToShooter extends CommandGroup {

	public WaitAndMoveToShooter() {
		addSequential(new WaitCommand(Constants.TIME_GAP_FOR_SHOOTING));
		addSequential(new MoveToShooter(), Constants.ROLL_IN_TIME);
	}

}
