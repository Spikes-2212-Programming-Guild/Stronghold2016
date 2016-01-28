package com.spikes2212.robot2016.commands.advanced;

import com.spikes2212.robot2016.commands.advanced.Cross.Direction;
import com.spikes2212.robot2016.commands.drivetrain.StraightDriveByDistance;
import com.spikes2212.robot2016.commands.roller.folder.ExpandRoller;
import com.spikes2212.robot2016.commands.roller.folder.RetractRoller;
import com.spikes2212.robot2016.commands.triz.MoveTrizDown;
import com.spikes2212.robot2016.commands.triz.MoveTrizUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossRockWall extends CommandGroup {
    
	public static final double DISTANCE = 0;
	
    public CrossRockWall(Direction direction) {
    	//TODO: Make sure that MoveTrizUp is longer than RetractRolller
    	addParallel(new RetractRoller());
    	addSequential(new MoveTrizUp());
    	addSequential(new StraightDriveByDistance(direction.getSpeedDirection() * DISTANCE));
    }
}
