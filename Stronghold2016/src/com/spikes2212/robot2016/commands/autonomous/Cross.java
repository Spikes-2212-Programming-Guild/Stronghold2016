package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.Defense;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Cross extends CommandGroup {

	public Cross(Defense defense) {
		switch (defense) {
		case CHEVAL_DE_FRISE:
			addSequential(new CrossChevalDeFrise());
			break;
		case LOW_BAR:
			addSequential(new CrossLowBar());
			break;
		case MOAT:
			addSequential(new CrossMoat());
			break;
		case PORTCULLIS:
			addSequential(new CrossPortcullis());
			break;
		case ROCK_WALL:
			addSequential(new CrossRockWall());
			break;
		case ROUGH_TERRAIN:
			addSequential(new CrossRoughTerrain());
			break;
		default:
			break;
		}
	}
}