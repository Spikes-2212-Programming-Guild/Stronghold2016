package com.spikes2212.robot2016.subsystems;

import com.spikes2212.robot2016.util.Gearbox;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Drivetrain extends Subsystem {
	private Gearbox left, right;

	private Gyro gyro;

	public Drivetrain(Gyro gyro, Gearbox left, Gearbox right) {
		this.left = left;
		this.right = right;
		this.gyro = gyro;
	}

	public void forward(double speed) {
		setTwoSides(speed, speed);
	}

	public void turn(double speed) {
		setTwoSides(speed, -speed);
	}

	public double getYawAngle() {
		return gyro.getAngle();
	}

	public double getYawRate() {
		return gyro.getRate();
	}

	// arcade
	public void stop() {
		setTwoSides(0, 0);
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
		setTwoSides(-leftSpeed, rightSpeed);
	}

	@Override
	protected void initDefaultCommand() {

	}

	public double getLeftDistance() {
		return -left.getDistance();
	}

	public double getRightDistance() {
		return right.getDistance();
	}
}
