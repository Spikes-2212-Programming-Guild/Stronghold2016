package com.spikes2212.robot2016.commands.advanced;

import com.spikes2212.robot2016.commands.advanced.Cross.Direction;
import com.spikes2212.robot2016.commands.drivetrain.StraightDriveByDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossChevalDeFrise extends CommandGroup {
    
	public static final double DISTANCE = 0;
	
    public CrossChevalDeFrise(Direction direction) {
    	addSequential(new StraightDriveByDistance(direction.getSpeedDirection() * DISTANCE));
    }
}
