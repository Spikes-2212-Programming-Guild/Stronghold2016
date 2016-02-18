package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.Direction;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.drivetrain.PIDTurnDriveByAngle;
import com.spikes2212.robot2016.commands.triz.MoveTrizDown;
import com.spikes2212.robot2016.commands.triz.MoveTrizUp;
import com.spikes2212.robot2016.commands.triz.PIDMoveTriz;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author alognruny
 *
 *         WARNING! in order to use this command correctly, the robot MUST be
 *         placed with the triz facing the outerworks.
 *
 */
public class CrossChevalDeFrise extends CommandGroup {

	public static final double ROTATE_ANGLE = 180;
	public static final double FORWARD_DISTANCE = 0;
	public static final double FORWARD_AFTER_LIFTING_DISTANCE = 0;
	public static final double BACKWARD_DISTANCE = 0;
	public static final double BACKWARD_AFTER_LIFTING_DISTANCE = 0;
	

	public CrossChevalDeFrise(Direction direction) {
		if(direction==Direction.FORWARD){
			addSequential(new PIDStraightDriveByDistance(FORWARD_DISTANCE));
			addSequential(new MoveTrizDown());
			addSequential(new PIDStraightDriveByDistance(FORWARD_AFTER_LIFTING_DISTANCE));
		}else{
			addSequential(new MoveTrizUp());
			addSequential(new PIDTurnDriveByAngle(ROTATE_ANGLE));
			addSequential(new PIDStraightDriveByDistance(BACKWARD_DISTANCE));
			addSequential(new MoveTrizDown());
			addSequential(new PIDStraightDriveByDistance(BACKWARD_AFTER_LIFTING_DISTANCE));
		}
	}

}
