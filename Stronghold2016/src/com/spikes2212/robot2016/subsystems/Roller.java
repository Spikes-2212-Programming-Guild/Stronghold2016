package com.spikes2212.robot2016.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * 
 * @author Itamar
 *
 */
public class Roller extends Subsystem {
	private TalonSRX boulder, folder;
	private DigitalInput folderLimitExpand, folderLimitRetract, boulderLimit;
	private AnalogInput distanceSensor;

	/**
	 * @param boulder
	 * @param folder
	 * @param folderLimitExpand
	 * @param folderLimitRetract
	 */
	public Roller(TalonSRX boulder, TalonSRX folder, DigitalInput folderLimitExpand, DigitalInput folderLimitRetract,
			AnalogInput distanceSensor, DigitalInput boulderLimit) {
		this.boulder = boulder;
		this.folder = folder;
		this.folderLimitExpand = folderLimitExpand;
		this.folderLimitRetract = folderLimitRetract;
		this.distanceSensor = distanceSensor;
		this.boulderLimit = boulderLimit;
	}

	public Roller(int boulderPort, int folderPort, int folderLimitExpand, int folderLimitRetract,
			int distanceSensorPort, int boulderLimitPort) {
		this(new TalonSRX(boulderPort), new TalonSRX(folderPort), new DigitalInput(folderLimitExpand),
				new DigitalInput(folderLimitExpand), new AnalogInput(distanceSensorPort),
				new DigitalInput(boulderLimitPort));
	}

	public void fold(double speed) {
		folder.set(speed);
	}

	public void roll(double speed) {
		boulder.set(speed);
	}

	public boolean canExpand() {
		return folderLimitExpand.get();
	}

	public boolean canRetract() {
		return folderLimitRetract.get();
	}

	public boolean detectBoulder() {
		return (distanceSensor.getAverageVoltage()
				* com.spikes2212.robot2016.consts.RollerConsts.VOLTAGE_TO_DISTANCE_CONVERSION < com.spikes2212.robot2016.consts.RollerConsts.BOULDER_DETECTION_THRESHOLD)
				&& !hasBoulder();
	}

	public boolean hasBoulder() {
		return boulderLimit.get();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public void stopFold() {
		folder.stopMotor();
	}
}
