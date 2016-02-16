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
	private DigitalInput contracted, expanded;
	private Encoder encoder;
	private double phase;

	public Folder(SpeedController motor, DigitalInput contracted, DigitalInput expanded, Encoder encoder) {
		this.motor = motor;
		this.contracted = contracted;
		this.expanded = expanded;
		this.encoder = encoder;
		this.encoder.setDistancePerPulse(Constants.FOLDER_ANGLE_PER_PULSE);
		this.phase = Constants.FOLDER_CONTRACTED_ANGLE;

	}

	public Folder(int motorChannel, int upLimitSwitch, int downLimitSwitch, int encoderAPort, int encoderBPort) {
		this(new VictorSP(motorChannel), new DigitalInput(upLimitSwitch), new DigitalInput(downLimitSwitch),
				new Encoder(encoderAPort, encoderBPort));

	}

	public boolean canMove(double speed) {
		return !(speed > 0 && isContracted() || speed < 0 && isExpanded());
	}

	public void tryMove(double speed) {
		if (canMove(speed)) {
			motor.set(speed);
		}
	}

	public boolean isContracted() {
		return !contracted.get();
	}

	public boolean isExpanded() {
		return !expanded.get();
	}

	public void stop() {
		motor.set(0);
	}

	public void calibrate() {
		if (isContracted()) {
			phase = Constants.FOLDER_CONTRACTED_ANGLE;
		} else if (isExpanded()) {
			phase = Constants.FOLDER_EXPANDED_ANGLE;
		} else {
			phase = getAngle();
		}
		encoder.reset();
	}

	public double getAngle() {
		return phase + encoder.getDistance();
	}

	@Override
	public void initDefaultCommand() {
	}
}
