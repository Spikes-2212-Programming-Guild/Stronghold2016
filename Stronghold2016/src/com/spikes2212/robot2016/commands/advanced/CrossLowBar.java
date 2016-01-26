package com.spikes2212.robot2016.commands.advanced;

import com.spikes2212.robot2016.Permanents;
import com.spikes2212.robot2016.commands.drivetrain.StraightDriveByDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossLowBar extends CommandGroup {
    
    public CrossLowBar() {
    	addSequential(new StraightDriveByDistance(Permanents.LOW_BAR_DISTANCE));
    }
}
