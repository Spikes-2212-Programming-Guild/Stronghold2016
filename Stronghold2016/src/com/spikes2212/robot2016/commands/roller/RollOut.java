package com.spikes2212.robot2016.commands.roller;

import static com.spikes2212.robot2016.Robot.roller;

import com.spikes2212.robot2016.consts.RollerConsts;

import edu.wpi.first.wpilibj.command.Command;

public class RollOut extends Command {

	@Override
	protected void initialize() {
		requires(roller);
	}

	@Override
	protected void execute() {
		roller.roll(RollerConsts.ROLL_OUT_SPEED);
	}

	@Override
	protected boolean isFinished() {
		return !roller.hasBoulder();
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
