package com.spikes2212.robot2016.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.DIOJNI;

/**
 *
 */
public class Triz extends Subsystem {
	private Talon trizTalon;
	private DigitalInput upLimit, downLimit;

	public Triz(Talon trizTalon, DigitalInput up, DigitalInput down) {
		this.trizTalon = trizTalon;
		this.upLimit = up;
		this.downLimit = down;
	}

	public Triz(int trizTalonPort, int upPort, int downPort) {
		trizTalon = new Talon(trizTalonPort);
		upLimit = new DigitalInput(upPort);
		downLimit = new DigitalInput(downPort);
	}

	public void moveTriz(double speed) {
		trizTalon.set(speed);
	}

	public void stop() {
		trizTalon.set(0);
	}

	public boolean isUp() {
		return !upLimit.get();
	}

	public boolean isDown() {
		return !downLimit.get();
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
	}
}
