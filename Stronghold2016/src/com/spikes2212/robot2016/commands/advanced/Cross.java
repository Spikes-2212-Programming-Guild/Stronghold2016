package com.spikes2212.robot2016.commands.advanced;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Cross extends CommandGroup {
	public enum Direction {
		TO_COUTYARD, TO_NEUTRAL_ZONE
	}

	public enum Defense {
		LOW_BAR, PORTCULLIS, CHEVAL_DE_FRISE, MOAT, RAMPARTS, DRAWBRIDGE, SALLY_PORT, ROCK_WALL, ROUGH_TERRAIN
	}
	private Defense defense;
	private Direction direction;

	public Cross(Defense defense, Direction direction) {
		this.defense = defense;
		this.direction = direction;
	}
}