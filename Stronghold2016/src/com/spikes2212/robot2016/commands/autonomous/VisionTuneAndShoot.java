package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.commands.drivetrain.VisionTuneToDistance;
import com.spikes2212.robot2016.commands.folder.PIDMoveFolder;
import com.spikes2212.robot2016.commands.shooter.ShootByVoltage;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class VisionTuneAndShoot extends CommandGroup {

	public VisionTuneAndShoot(double wantedDistance, double folderPosition, double voltage) {
		addParallel(new VisionTuneToDistance(wantedDistance));
		addSequential(new PIDMoveFolder(folderPosition));
		addSequential(new ShootByVoltage(voltage));
	}

}
