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
public class CrossPortcullis extends CommandGroup {

	public static final double ROTATE_ANGLE = 180;
	public static final double DRIVE_TO_PORTCULLIS_DISTANCE = 0;
	public static final double AFTER_LIFTING_PORTCULLIS_DISTANCE = 0;

	public CrossPortcullis(/*Direction direction*/) {
		
		addSequential(new PIDStraightDriveByDistance(DRIVE_TO_PORTCULLIS_DISTANCE ));
		addSequential(new MoveTrizDown());
		addSequential(new PIDStraightDriveByDistance(AFTER_LIFTING_PORTCULLIS_DISTANCE));
	}

}
