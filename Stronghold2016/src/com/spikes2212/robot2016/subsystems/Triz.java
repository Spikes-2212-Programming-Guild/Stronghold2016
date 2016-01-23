package com.spikes2212.robot2016.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Triz extends Subsystem {
	Talon trizTalon;

	Triz(Talon trizTalon) {
		this.trizTalon = trizTalon;
	}

	public void moveTriz(double speed) {
		trizTalon.set(speed);
	}

	public void stop() {
		trizTalon.set(0);
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
