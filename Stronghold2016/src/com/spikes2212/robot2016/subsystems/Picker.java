package com.spikes2212.robot2016.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Picker extends Subsystem {
	private SpeedController motor;
	private DigitalInput ballDetector;

	public Picker(SpeedController motor, DigitalInput ballDetector) {
		this.motor = motor;
		this.ballDetector = ballDetector;
	}

	public Picker(int motorChannel, int ballDetectorChannel) {
		this(new VictorSP(motorChannel), new DigitalInput(ballDetectorChannel));
	}

	public void roll(double speed) {
		motor.set(speed);
	}

	public void stop() {
		motor.set(0);
	}

	public boolean isBoulderInside() {
		return !ballDetector.get();
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
