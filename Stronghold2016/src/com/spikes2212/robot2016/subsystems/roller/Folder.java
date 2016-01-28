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
	private DigitalInput up, down;

	public Folder(SpeedController motor, DigitalInput up, DigitalInput down) {
		this.motor = motor;
		this.up = up;
		this.down = down;
	}

	public Folder(int motorChannel, int upLimitSwitch, int downLimitSwitch) {
		this(new VictorSP(motorChannel), new DigitalInput(upLimitSwitch),
				new DigitalInput(downLimitSwitch));

	}

	public void moveFolder(double speed) {
		motor.set(speed);
	}

	public boolean isUp() {
		return up.get();
	}

	public boolean isDown() {
		return down.get();
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
