package com.spikes2212.robot2016.subsystems;

import com.spikes2212.robot2016.util.Gearbox;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {
	Gearbox left, right;

	public Drivetrain(Gearbox left, Gearbox right) {
		this.left = left;
		this.right = right;
	}

	public Drivetrain() {
	}

	public void forward(double speed) {
		right.set(speed);
		left.set(-speed);
	}

	public void turn(double speed) {
		right.set(speed);
		left.set(speed);
	}

	// arcade
	public void stop() {
		this.forward(0);
	}

	public void twoJoysticksDriving(double leftSpeed, double rightSpeed) {
		setTwoSides(leftSpeed, rightSpeed);
	}

	public void arcade(double moveValue, double rotateValue) {
		double leftSpeed, rightSpeed;
		if (moveValue > 0.0) {
			if (rotateValue > 0.0) {
				leftSpeed = moveValue - rotateValue;
				rightSpeed = Math.max(moveValue, rotateValue);
			} else {
				leftSpeed = Math.max(moveValue, -rotateValue);
				rightSpeed = moveValue + rotateValue;
			}
		} else {
			if (rotateValue > 0.0) {
				leftSpeed = -Math.max(-moveValue, rotateValue);
				rightSpeed = moveValue + rotateValue;
			} else {
				leftSpeed = moveValue - rotateValue;
				rightSpeed = -Math.max(-moveValue, -rotateValue);
			}
		}
		setTwoSides(leftSpeed, rightSpeed);
	}

	public void setTwoSides(double leftSpeed, double rightSpeed) {
		left.set(-leftSpeed);
		right.set(rightSpeed);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}
