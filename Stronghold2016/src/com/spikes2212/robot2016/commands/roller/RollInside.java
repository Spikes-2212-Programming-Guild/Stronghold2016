package com.spikes2212.robot2016.commands.roller;

import com.spikes2212.robot2016.Robot;
import com.spikes2212.robot2016.consts.RollerConsts;

import edu.wpi.first.wpilibj.command.Command;
import static com.spikes2212.robot2016.Robot.roller;

public class RollInside extends Command {

	@Override
	protected void initialize() {
		requires(roller);
	}

	@Override
	protected void execute() {
		if (!isFinished())
			Robot.roller.roll(RollerConsts.ROLL_IN_SPEED);
	}

	@Override
	protected boolean isFinished() {
		return roller.hasBoulder();
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
