package com.spikes2212.robot2016.commands.advanced;

import com.spikes2212.robot2016.commands.advanced.Field.Defense;
import com.spikes2212.robot2016.commands.advanced.Field.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Cross extends CommandGroup {

	public Cross(Defense defense, Direction direction) {
		switch (defense) {
		case CHEVAL_DE_FRISE:
			addSequential(new CrossChevalDeFrise(direction));
			break;
		case LOW_BAR:
			addSequential(new CrossLowBar(direction));
			break;
		case MOAT:
			addSequential(new CrossMoat(direction));
			break;
		case PORTCULLIS:
			addSequential(new CrossPortcullis(direction));
			break;
		case RAMPARTS:
			addSequential(new CrossRamparts(direction));
			break;
		case ROCK_WALL:
			addSequential(new CrossRockWall(direction));
			break;
		case ROUGH_TERRAIN:
			addSequential(new CrossRoughTerrain(direction));
			break;
		default:
			break;
		}
	}
}