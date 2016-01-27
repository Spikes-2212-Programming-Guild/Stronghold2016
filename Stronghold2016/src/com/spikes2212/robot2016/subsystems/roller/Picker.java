package com.spikes2212.robot2016.subsystems.roller;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Picker extends Subsystem {
	private TalonSRX pickerTalon;
	private DigitalInput ballLimit;

	public Picker(TalonSRX pickerTalon, DigitalInput ballLimit) {
		this.pickerTalon = pickerTalon;
		this.ballLimit = ballLimit;
	}

	public Picker(int pickerTalonPort, int ballLimitChannel) {
		this(new TalonSRX(pickerTalonPort), new DigitalInput(ballLimitChannel));
	}

	public void roll(double speed) {
		pickerTalon.set(speed);
	}

	public void stop() {
		pickerTalon.set(0);
	}

	public boolean isBallInside() {
		return !ballLimit.get();
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
