package com.spikes2212.robot2016.subsystems.roller;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Folder extends Subsystem {
	private SpeedController motor;
	DigitalInput open, closed;

	public Folder(SpeedController motor, DigitalInput open, DigitalInput closed) {
		this.motor = motor;
		this.open = open;
		this.closed = closed;
	}

	public Folder(int motorChannel, int openLimitSwitch, int closedLimitSwitch) {
		this(new VictorSP(motorChannel), new DigitalInput(openLimitSwitch),
				new DigitalInput(closedLimitSwitch));

	}

	public void moveFolder(double speed) {
		motor.set(speed);
	}

	public boolean isOpen() {
		return open.get();
	}

	public boolean isClose() {
		return closed.get();
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
