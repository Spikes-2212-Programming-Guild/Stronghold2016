package com.spikes2212.robot2016.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 * @author Itamar
 */
public class Triz extends Subsystem {
	private TalonSRX trizFolder;
	private DigitalInput foldLimit, unfoldLimit;
	private Potentiometer trizPressure;

	/**
	 * @param trizFolder
	 * @param foldLimit
	 * @param unfoldLimit
	 * @param trizPressure
	 */
	public Triz(TalonSRX trizFolder, DigitalInput foldLimit, DigitalInput unfoldLimit,
			AnalogPotentiometer trizPressure) {
		this.trizFolder = trizFolder;
		this.foldLimit = foldLimit;
		this.unfoldLimit = unfoldLimit;
		this.trizPressure = trizPressure;
	}

	/**
	 * the last parameter is the port for an ANALOG INPUT
	 */
	public Triz(int trizFolderPort, int foldLimitPort, int unfoldLimitPort, int trizPressurePort) {
		this(new TalonSRX(trizFolderPort), new DigitalInput(foldLimitPort), new DigitalInput(unfoldLimitPort),
				new AnalogPotentiometer(new AnalogInput(trizPressurePort)));
	}

	public void fold(double speed) {
		trizFolder.set(Math.abs(speed));
	}

	public void unfold(double speed) {
		trizFolder.set((-1) * Math.abs(speed));
	}

	public boolean isHoldingGate() {
		return trizPressure.get() != 0;
	}

	public boolean canFold() {
		return !foldLimit.get();
	}

	public boolean canUnfold() {
		return !unfoldLimit.get();
	}

	public void stop() {
		trizFolder.stopMotor();
	}

	public void initDefaultCommand() {

	}
}
