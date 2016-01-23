package com.spikes2212.robot2016.commands.lowbar;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com.spikes2212.robot2016.commands.drivetrain.*;

/**
 * @author Itamar
 */
public class FoldingAll extends CommandGroup {

	public FoldingAll() {
		// TODO: add triz fold
		// addParallel(new FoldingTriz());
		addParallel(new FoldingRoller());
		addParallel(new JoystickArcadeDrive());

	}
}
