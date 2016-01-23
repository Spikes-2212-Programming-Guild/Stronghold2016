package com.spikes2212.robot2016.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Itamar
 */
public class Folder extends Subsystem {
	private TalonSRX folder;
	private DigitalInput folderLimitUnfold, folderLimitFold;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	/**
	 * @param folder
	 * @param folderLimitUnfold
	 * @param folderLimitFold
	 */
	public Folder(TalonSRX folder, DigitalInput folderLimitUnfold, DigitalInput folderLimitFold) {
		this.folder = folder;
		this.folderLimitUnfold = folderLimitUnfold;
		this.folderLimitFold = folderLimitFold;
	}

	public Folder(int folderPort, int folderLimitFoldPort, int folderLimitUnfoldPort) {
		this(new TalonSRX(folderPort), new DigitalInput(folderLimitFoldPort), new DigitalInput(folderLimitUnfoldPort));
	}

	public void stopFold() {
		folder.stopMotor();
	}

	public void fold(double speed) {
		folder.set(speed);
	}
	public boolean canUnfold() {
		return folderLimitUnfold.get();
	}

	public boolean canFold() {
		return folderLimitFold.get();
	}

	public void initDefaultCommand() {
	}
}
