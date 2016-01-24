package com.spikes2212.robot2016.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.spikes2212.robot2016.Permanents;

import edu.wpi.first.wpilibj.TalonSRX;

/**
 *
 */
public class Folder extends Subsystem {
	private TalonSRX folderTalon;

	public Folder(TalonSRX folderTalon) {
		this.folderTalon = folderTalon;
	}

	public void moveFolder(double speed) {
		folderTalon.set(speed);
	}

	public void stop() {
		folderTalon.set(0);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
