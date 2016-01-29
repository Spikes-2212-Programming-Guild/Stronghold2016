package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field.DefenseLocation;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ReachTowerFromDefense extends CommandGroup {

	public ReachTowerFromDefense(DefenseLocation location) {
		switch (location) {
		case k1:
			addSequential(new ReachTowerFromDefense1());
			break;
		case k2:
			addSequential(new ReachTowerFromDefense2());
			break;
		case k3:
			addSequential(new ReachTowerFromDefense3());
			break;
		case k4:
			addSequential(new ReachTowerFromDefense4());
			break;
		case k5:
			addSequential(new ReachTowerFromDefense5());
			break;
		default:
			break;
		}
	}

}
