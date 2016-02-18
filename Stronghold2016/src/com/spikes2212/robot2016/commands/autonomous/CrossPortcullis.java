package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.Direction;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.drivetrain.PIDTurnDriveByAngle;
import com.spikes2212.robot2016.commands.triz.MoveTrizDown;
import com.spikes2212.robot2016.commands.triz.MoveTrizUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossPortcullis extends CommandGroup {
	
	public static final double ROTATE_ANGLE = 180;
	public static final double FORWARD_DISTANCE = 0;
	public static final double FORWARD_AFTER_LIFTING_DISTANCE = 0;
	public static final double BACKWARD_DISTANCE = 0;
	public static final double BACKWARD_AFTER_LIFTING_DISTANCE = 0;
	

	public CrossPortcullis(Direction direction) {
		if(direction==Direction.FORWARD){
			addSequential(new PIDStraightDriveByDistance(FORWARD_DISTANCE));
			addSequential(new MoveTrizUp());
			addSequential(new PIDStraightDriveByDistance(FORWARD_AFTER_LIFTING_DISTANCE));
		}else{
			addSequential(new MoveTrizDown());
			addSequential(new PIDTurnDriveByAngle(ROTATE_ANGLE));
			addSequential(new PIDStraightDriveByDistance(BACKWARD_DISTANCE));
			addSequential(new MoveTrizUp());
			addSequential(new PIDStraightDriveByDistance(BACKWARD_AFTER_LIFTING_DISTANCE));
		}
	}
}
