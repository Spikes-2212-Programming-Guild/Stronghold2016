package com.spikes2212.robot2016.commands.lowbar;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com.spikes2212.robot2016.commands.drivetrain.*;
import com.spikes2212.robot2016.commands.triz.*;

/**
 * @author Itamar
 */
public class FoldingAll extends CommandGroup {

	public FoldingAll() {
		addParallel(new CloseTriz());
		addParallel(new FoldingRoller());
		addParallel(new JoystickArcadeDrive());

	}
}
