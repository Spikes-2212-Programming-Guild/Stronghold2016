package com.spikes2212.robot2016.subsystems;

import com.spikes2212.robot2016.util.Gearbox;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Drivetrain extends Subsystem {
	private Gearbox left, right;
	private Encoder leftFrontEncoder, leftRearEncoder, rightFrontEncoder, rightRearEncoder;

	private Gyro gyro;

	public Drivetrain(Gyro gyro, Gearbox left, Gearbox right) {
		this.left = left;
		this.right = right;
		this.gyro = gyro;
	}

	public Drivetrain(Gyro gyro, VictorSP leftFront, VictorSP leftRear, VictorSP rightFront, VictorSP rightRear,
			Encoder encoderLeftFront, Encoder encoderLeftRear, Encoder encoderRightFront, Encoder encoderRightRear) {
		this(gyro, new Gearbox(leftFront, leftRear, encoderLeftFront, encoderLeftRear),
				new Gearbox(rightFront, rightRear, encoderRightFront, encoderRightRear));

	}

	public void forward(double speed) {
		left.set(speed);
		right.set(-speed);
	}

	public void turn(double speed) {
		right.set(speed);
		left.set(speed);
	}

	public double getAngle() {
		return gyro.getAngle();
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
