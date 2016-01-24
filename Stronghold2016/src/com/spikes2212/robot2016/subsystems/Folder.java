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

	public void open() {
		folderTalon.set(Permanents.FOLDER_OPEN_SPEED);
	}

	public void close() {
		folderTalon.set(Permanents.FOLDER_CLOSE_SPEED);
	}

	public void foldBySpeed(double speed) {
		folderTalon.set(speed);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
