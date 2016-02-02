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
		k1, k2, k3, k4, k5

	}

	public enum Goal {
		LOW, HIGH
	}
}
