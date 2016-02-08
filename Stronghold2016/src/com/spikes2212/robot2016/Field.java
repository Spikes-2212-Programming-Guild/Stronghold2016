package com.spikes2212.robot2016;

public class Field {
	public enum Direction {
		FORWARD {
			@Override
			public double getSpeedDirection() {
				return 1;
			}
		},
		BACKWARD {
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

	public enum DefenseLocation {
		k1, k2, k3, k4, k5;
		
	}

	public enum Goal {
		LOW, HIGH
	}
	
	public static /*final*/ double FIELD_WIDTH = 8.1026,
			HORIZONTAL_DISTANCED_BETWEEN_OUTWER_WORKS_AND_TOWER = 2.258,
			OUTER_WORKS_TOTAL_LENGTH = 6.752,
			SINGLE_DEFNSE_LENGTH = OUTER_WORKS_TOTAL_LENGTH / 5;
	
	public static double getDistanceFromDefenseToTower(int slot){
		double horizontalDistance = FIELD_WIDTH / 2 //location of tower
										- SINGLE_DEFNSE_LENGTH * (slot - 0.5);
		return Math.sqrt(horizontalDistance * horizontalDistance + 
						HORIZONTAL_DISTANCED_BETWEEN_OUTWER_WORKS_AND_TOWER
						* HORIZONTAL_DISTANCED_BETWEEN_OUTWER_WORKS_AND_TOWER);
	}
	
	public static double getAngleToTurnFromDefenseTowardsTower(int slot){
		double totalDistance = getDistanceFromDefenseToTower(slot);
		return Math.acos(HORIZONTAL_DISTANCED_BETWEEN_OUTWER_WORKS_AND_TOWER / totalDistance);
	}
	
	
}
