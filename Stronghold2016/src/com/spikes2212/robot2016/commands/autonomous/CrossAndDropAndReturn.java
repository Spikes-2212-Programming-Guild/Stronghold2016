package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Calibrate;
import com.spikes2212.robot2016.Field.Defense;
import com.spikes2212.robot2016.Field.Direction;
import com.spikes2212.robot2016.commands.picker.RollOut;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CrossAndDropAndReturn extends CommandGroup {

	@Calibrate(desc = "Choose a fittting value", unit = "second")
	public static final double WAIT_TIME = 0;

	public CrossAndDropAndReturn(Defense defense) {
		addSequential(new Cross(defense, Direction.FORWARD));
		addSequential(new RollOut());
		addSequential(new WaitCommand(WAIT_TIME));
		addSequential(new Cross(defense, Direction.BACKWARD));
	}

}
