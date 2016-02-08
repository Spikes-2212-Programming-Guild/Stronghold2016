package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Calibrate;
import com.spikes2212.robot2016.Field.Defense;
import com.spikes2212.robot2016.Field.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CrossAndReturn extends CommandGroup {

	@Calibrate(desc = "Choose a fittting value", unit = "second")
	public static final double WAIT_TIME = 0;

	public CrossAndReturn(Defense defense) {
		addSequential(new Cross(defense, Direction.FORWARD));
		addSequential(new WaitCommand(WAIT_TIME));
		addSequential(new Cross(defense, Direction.BACKWARD));
	}

}
