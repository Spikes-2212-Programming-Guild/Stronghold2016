package com.spikes2212.robot2016.subsystems.roller;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Picker extends Subsystem {
	private SpeedController motor;
	private DigitalInput ballLimit;

	public Picker(SpeedController motor, DigitalInput ballLimit) {
		this.motor = motor;
		this.ballLimit = ballLimit;
	}

	public Picker(int motorChannel, int ballLimitChannel) {
		this(new VictorSP(motorChannel), new DigitalInput(ballLimitChannel));
	}

	public void roll(double speed) {
		motor.set(speed);
	}

	public void stop() {
		motor.set(0);
	}

	public boolean isBallInside() {
		return !ballLimit.get();
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
