package com.spikes2212.robot2016.commands.advanced;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Cross extends CommandGroup {
	public enum Direction {
		FORWARD {
			@Override
			public double getSpeedDirection() {
				return 1;
			}
		}, BACKWARD {
			@Override
			public double getSpeedDirection() {
				return -1;
			}
		};
		
		public abstract double getSpeedDirection();
	}

	public enum Defense {
		LOW_BAR, PORTCULLIS, CHEVAL_DE_FRISE, MOAT, RAMPARTS, DRAWBRIDGE, SALLY_PORT, ROCK_WALL, ROUGH_TERRAIN
	}
	private Defense defense;
	private Direction direction;

	public Cross(Defense defense, Direction direction) {
		this.defense = defense;
		this.direction = direction;
		switch(defense) {
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
			addSequential(new CrossPortcullis());
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