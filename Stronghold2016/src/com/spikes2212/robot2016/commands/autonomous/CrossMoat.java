package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.commands.drivetrain.StraightDriveBySpeedAndTime;
import com.spikes2212.robot2016.commands.folder.MoveFolderDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossMoat extends CommandGroup {


	public CrossMoat() {
		addSequential(new MoveFolderDown(), 0.25);
		addSequential(new StraightDriveBySpeedAndTime(0.6, 4));
	}
}
