package com.spikes2212.robot2016.subsystems;

import com.spikes2212.robot2016.util.Gearbox;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {
	Gearbox right, left;

	public Drivetrain(Gearbox left, Gearbox right) {
		this.right = right;
		this.left = left;
	}

	public void straight(double speed) {
		left.set(-speed);
		right.set(speed);
	}

	public void turn(double speed) {
		left.set(speed);
		right.set(speed);
	}

	public double getLeftSpeed() {
		return left.getSpeed();
	}

	public double getRightSpeed() {
		return right.getSpeed();
	}

	@Override
	protected void initDefaultCommand() {
	}
}
