package com.spikes2212.robot2016.commands.shooter;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.commands.SequentialCommandGroup;
import com.spikes2212.robot2016.commands.drivetrain.TuneToTower;

public class TuneToTowerAndShoot extends SequentialCommandGroup {
	public TuneToTowerAndShoot() {
		super(new TuneToTower(), new ShootByVoltage(Constants.SHOOTING_VOLTAGE));
	}
}
