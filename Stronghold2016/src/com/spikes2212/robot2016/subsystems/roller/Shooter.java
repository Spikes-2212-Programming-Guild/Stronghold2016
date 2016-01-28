package com.spikes2212.robot2016.subsystems.roller;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
	private CANTalon motor;

	public Shooter(CANTalon motor) {
		this.motor = motor;
		this.motor.changeControlMode(TalonControlMode.Voltage);
		this.motor.enableControl();
	}

	public Shooter(int motorChannel) {
		this(new CANTalon(motorChannel));
	}

	public void shoot(double speed) {
		this.motor.set(speed);
	}

	public void stop() {
		this.motor.set(0);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
