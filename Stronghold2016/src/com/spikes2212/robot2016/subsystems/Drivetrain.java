package com.spikes2212.robot2016.subsystems;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.Robot;
import com.spikes2212.robot2016.commands.drivetrain.TwoJoysticksDrive;
import com.spikes2212.robot2016.util.Gearbox;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Drivetrain extends Subsystem {

	private Gearbox left, right;
	private Gyro gyro;
	private Accelerometer accelerometer;

	public Drivetrain(Gearbox left, Gearbox right, Gyro gyro, Accelerometer accelerometer) {
		this.left = left;
		this.right = right;
		this.gyro = gyro;
		this.accelerometer = accelerometer;
		left.setDistancePerPulse(Constants.LEFT_DISTANCE_PER_PULSE);
		right.setDistancePerPulse(Constants.RIGHT_DISTANCE_PER_PULSE);
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

	public double getZAcceleration() {
		return accelerometer.getZ();
	}

	public double getAngleWithFloor() {
		return Math.toDegrees(Math.acos(Math.max(-1, Math.min(1, getZAcceleration() / Constants.FREE_FALL_GRAVITY))));
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
		left.set(leftSpeed);
		right.set(-rightSpeed);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TwoJoysticksDrive(() -> -Robot.oi.leftDriver.getY(), () -> -Robot.oi.rightDriver.getY()));
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
		gyro.reset();
	}

	public double getLeftVelocity() {
		return left.getRate();
	}

	public double getRightVelocity() {
		return right.getRate();
	}

}
