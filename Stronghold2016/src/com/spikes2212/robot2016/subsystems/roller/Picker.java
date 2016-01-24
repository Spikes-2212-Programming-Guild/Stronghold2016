package com.spikes2212.robot2016.subsystems.roller;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Picker extends Subsystem {
	private TalonSRX pickerTalon;

	public Picker(TalonSRX pickerTalon) {
		this.pickerTalon = pickerTalon;
	}

	public Picker(int pickerTalonPort) {
		this(new TalonSRX(pickerTalonPort));
	}

	public void roll(double speed) {
		pickerTalon.set(speed);
	}

	public void stop() {
		pickerTalon.set(0);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
