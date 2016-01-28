package com.spikes2212.robot2016.subsystems.roller;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Folder extends Subsystem {
	private SpeedController motor;

	public Folder(SpeedController motor) {
		this.motor = motor;
	}

	public Folder(int motorChannel) {
		this(new VictorSP(motorChannel));
	}

	public void moveFolder(double speed) {
		motor.set(speed);
	}

	public void stop() {
		motor.set(0);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
