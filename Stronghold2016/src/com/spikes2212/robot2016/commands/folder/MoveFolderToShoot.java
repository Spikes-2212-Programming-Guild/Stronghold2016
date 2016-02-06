package com.spikes2212.robot2016.commands.folder;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author saargb
 *
 */

public class MoveFolderToShoot extends CommandGroup {

	public static final double POSITION = 0; // FIXME

	public MoveFolderToShoot() {
		addSequential(new PIDMoveFolder(POSITION));
	}

}