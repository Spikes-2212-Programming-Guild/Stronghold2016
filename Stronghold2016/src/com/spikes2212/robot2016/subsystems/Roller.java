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
	private TalonSRX roller;
	private DigitalInput boulderLimit;
	private AnalogInput distanceSensor;

	public Roller(TalonSRX roller, AnalogInput distanceSensor, DigitalInput boulderDetectionLimit) {
		this.roller = roller;
		this.distanceSensor = distanceSensor;
		this.boulderLimit = boulderDetectionLimit;
	}

	public Roller(int rollerPort, int distanceSensorPort, int boulderDetectionLimitPort) {
		this(new TalonSRX(rollerPort), new AnalogInput(distanceSensorPort),
				new DigitalInput(boulderDetectionLimitPort));
	}

	public void roll(double speed) {
		roller.set(speed);
	}

	public boolean detectBoulder() {
		return (distanceSensor.getAverageVoltage()
				//TODO: WTH, ever heard of imports?
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

}
