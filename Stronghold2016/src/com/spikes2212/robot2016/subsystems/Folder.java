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
		this.encoder.setDistancePerPulse(Constants.FOLDER_ANGLE_PER_PULSE);
		this.phase = Constants.FOLDER_UP_ANGLE;

	}

	public Folder(int motorChannel, int upLimitSwitch, int downLimitSwitch, int encoderAPort, int encoderBPort) {
		this(new VictorSP(motorChannel), new DigitalInput(upLimitSwitch), new DigitalInput(downLimitSwitch),
				new Encoder(encoderAPort, encoderBPort));

	}

	public boolean canMove(double speed) {
		return !(speed > 0 && isUp() || speed < 0 && isDown());
	}

	public void tryMove(double speed) {
		if (canMove(speed)) {
			motor.set(speed);
		}
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
			phase = Constants.FOLDER_UP_ANGLE;
		} else if (isDown()) {
			phase = Constants.FOLDER_DOWN_ANGLE;
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
