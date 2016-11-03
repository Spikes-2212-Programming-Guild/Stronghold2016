package com.spikes2212.robot2016.subsystems;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.Robot;
import com.spikes2212.robot2016.commands.drivetrain.JoystickArcadeDrive;
import com.spikes2212.robot2016.commands.drivetrain.JoystickForwardDrive;
import com.spikes2212.robot2016.util.Gearbox;
import com.spikes2212.robot2016.util.Util;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

	private Gearbox left, right;
	private double maxSpeed = Constants.VERY_HIGH_MAX_SPEED;

	public Drivetrain(Gearbox left, Gearbox right) {
		this.left = left;
		this.right = right;
		left.setDistancePerPulse(Constants.LEFT_DISTANCE_PER_PULSE);
		right.setDistancePerPulse(Constants.RIGHT_DISTANCE_PER_PULSE);
	}

	public void forward(double speed) {
		setTwoSides(speed, speed);
	}

	public void turn(double speed) {
		setTwoSides(speed, -speed);
	}

	@Deprecated
	public double getYawAngle() {
		return 0;
	}

	@Deprecated
	public double getYawRate() {
		return 0;
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
		left.set(maxSpeed * Util.limitAbs(-leftSpeed, 1));
		right.set(maxSpeed * Util.limitAbs(rightSpeed, 1));
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickForwardDrive(Robot.oi::getRightStraight));
	}

	public double getLeftDistance() {
		return -left.getDistance();
	}

	public double getRightDistance() {
		return right.getDistance();
	}

	public void resetEncoders() {
		left.reset();
		right.reset();
	}

	public void resetGyro() {
		// gyro.reset();
	}

	public double getLeftVelocity() {
		return -left.getRate();
	}

	public double getRightVelocity() {
		return right.getRate();
	}

	public void setMaximumSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getMaximumSpeed() {
		return maxSpeed;
	}

}
