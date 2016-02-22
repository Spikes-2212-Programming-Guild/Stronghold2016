package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.commands.ExpandAll;
import com.spikes2212.robot2016.commands.triz.MoveTrizUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveTrizDownish extends CommandGroup {

	public MoveTrizDownish() {
		addSequential(new ExpandAll());
		addSequential(new MoveTrizUp(Constants.TRIZ_UP_SPEED), 0.2);
	}

}
