package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.commands.ExpandAll;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Reach extends CommandGroup {

	public Reach() {
		addParallel(new ExpandAll());
		addSequential(new WaitCommand(1));
		addSequential(new PIDStraightDriveByDistance(1.4, 1.4 * 2.8 / 3.2, Constants.HIGH_MAX_SPEED));
	}

}
