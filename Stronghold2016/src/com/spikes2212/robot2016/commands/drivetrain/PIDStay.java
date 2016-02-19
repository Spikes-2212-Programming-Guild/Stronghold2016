package com.spikes2212.robot2016.commands.drivetrain;

public class PIDStay extends PIDStraightDriveByDistance {

	public PIDStay() {
		super(0);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
