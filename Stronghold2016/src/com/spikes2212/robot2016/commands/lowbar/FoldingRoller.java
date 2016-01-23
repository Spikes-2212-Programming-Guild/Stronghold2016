package com.spikes2212.robot2016.commands.lowbar;

import static com.spikes2212.robot2016.Robot.roller;
import com.spikes2212.robot2016.consts.RollerConsts;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Itamar
 */
public class FoldingRoller extends Command {
	// TODO: add Triz subsystem
	public FoldingRoller() {
		requires(roller);
	}

	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		roller.fold(RollerConsts.RETRACT_SPEED);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !roller.canRetract();
	}

	// Called once after isFinished returns true
	protected void end() {
		roller.stopFold();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
