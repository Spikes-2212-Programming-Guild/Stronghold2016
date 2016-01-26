package com.spikes2212.robot2016.commands.advanced;

import com.spikes2212.robot2016.Permanents;
import com.spikes2212.robot2016.RobotMap;
import com.spikes2212.robot2016.commands.Triz.LiftPortcullis;
import com.spikes2212.robot2016.commands.drivetrain.StraightDriveByDistance;
import com.spikes2212.robot2016.commands.drivetrain.StraightDriveToPortcullis;
import com.spikes2212.robot2016.commands.roller.folder.ExpandRoller;
import com.spikes2212.robot2016.commands.triz.CloseTriz;
import com.spikes2212.robot2016.commands.triz.OpenTriz;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossPortcullis extends CommandGroup { 
    public  CrossPortcullis() {
    	addSequential(new StraightDriveToPortcullis(Permanents.DRIVE_TO_PORTCULLIS_VELOCITY));
    	addSequential(new LiftPortcullis(Permanents.LIFTING_PORTCULLIS_DISTANCE));
    	addSequential(new StraightDriveByDistance(Permanents.AFTER_LIFTING_PORTCULLIS_DISTANCE));
    }
}
