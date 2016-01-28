package com.spikes2212.robot2016.commands.advanced;

import com.spikes2212.robot2016.commands.advanced.Cross.Direction;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.roller.folder.MoveFolderDown;
import com.spikes2212.robot2016.commands.triz.MoveTrizUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossRockWall extends CommandGroup {
    
	public static final double DISTANCE = 0;
	
    public CrossRockWall(Direction direction) {
    	//TODO: Make sure that MoveTrizUp is longer than RetractRolller
    	addParallel(new MoveFolderDown());
    	addSequential(new MoveTrizUp());
    	addSequential(new PIDStraightDriveByDistance(direction.getSpeedDirection() * DISTANCE));
    }
}
