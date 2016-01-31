package com.spikes2212.robot2016.subsystems;

import com.spikes2212.robot2016.Constants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Folder extends Subsystem {
	private SpeedController motor;
	private DigitalInput up, down;
	private Encoder encoder;
	private double phase;

	public Folder(SpeedController motor, DigitalInput up, DigitalInput down, Encoder encoder) {
		this.motor = motor;
		this.up = up;
		this.down = down;
		this.encoder = encoder;

	}

	public Folder(int motorChannel, int upLimitSwitch, int downLimitSwitch, int encoderAPort, int encoderBPort) {
		this(new VictorSP(motorChannel), new DigitalInput(upLimitSwitch), new DigitalInput(downLimitSwitch),
				new Encoder(encoderAPort, encoderBPort));

	}

	public void moveFolder(double speed) {
		motor.set(speed);
	}

	public boolean isUp() {
		return !up.get();
	}

	public boolean isDown() {
		return !down.get();
	}

	public void stop() {
		motor.set(0);
	}

	public void calibrate() {
		if (isUp()) {
			phase = Constants.FOLDER_UP_DISTANCE;
			encoder.reset();
		}
		if (isDown()) {
			phase = Constants.FOLDER_DOWN_DISTANCE;
			encoder.reset();
		}
	}

	public double getDistance() {
		return phase + encoder.getDistance();
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
