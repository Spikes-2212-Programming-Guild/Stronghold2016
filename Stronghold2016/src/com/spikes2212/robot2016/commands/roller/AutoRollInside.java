package com.spikes2212.robot2016.commands.roller;

import com.spikes2212.robot2016.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author Itamar
 *
 */
public class AutoRollInside extends CommandGroup {

	public AutoRollInside() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.
		requires(Robot.roller);
		addParallel(new ExpandRollers());
		addParallel(new RollInside());
		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}
