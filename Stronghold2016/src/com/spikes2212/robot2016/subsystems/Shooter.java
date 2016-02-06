package com.spikes2212.robot2016.subsystems;

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

	public void shoot(double voltage) {
		this.motor.set(voltage);
	}

	public void stop() {
		this.motor.set(0);
	}

	@Override
	public void initDefaultCommand() {
	}
}
