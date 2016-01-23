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
	private DigitalInput up, down; 

	public Triz(Talon trizTalon, DigitalInput up, DigitalInput down) {
		this.trizTalon = trizTalon;
		this.up=up;
		this.down=down;
	}

	public Triz(int trizTalonPort,int upPort,int downPort) {
		trizTalon = new Talon(trizTalonPort);
		up = new DigitalInput(upPort);
		down= new DigitalInput(downPort);
	}

	public void moveTriz(double speed) {
		trizTalon.set(speed);
	}

	public void stop() {
		trizTalon.set(0);
	}
	public boolean isUp() {
		return up.get();
	}
	public boolean isDown() {
		return down.get();
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
