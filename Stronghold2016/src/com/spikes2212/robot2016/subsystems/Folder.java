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
	private DigitalInput down, up;
	private Encoder encoder;
	private double phase;

	public Folder(SpeedController motor, DigitalInput up, DigitalInput down, Encoder encoder) {
		this.motor = motor;
		this.down = down;
		this.up = up;
		this.encoder = encoder;
		this.encoder.setDistancePerPulse(Constants.FOLDER_ANGLE_PER_PULSE);
		this.phase = Constants.FOLDER_DOWN_ANGLE;

	}

	public Folder(int motorChannel, int upLimitSwitch, int downLimitSwitch, int encoderAPort, int encoderBPort) {
		this(new VictorSP(motorChannel), new DigitalInput(upLimitSwitch), new DigitalInput(downLimitSwitch),
				new Encoder(encoderAPort, encoderBPort));

	}

	public boolean canMove(double speed) {
		return !(speed > 0 && isDown() || speed < 0 && isUp());
	}

	public void tryMove(double speed) {
		if (canMove(speed)) {
			motor.set(speed);
		}
	}

	public boolean isDown() {
		return !down.get();
	}

	public boolean isUp() {
		return !up.get();
	}

	public void stop() {
		motor.set(0);
	}

	public void calibrate() {
	}

	public double getAngle() {
		return phase + encoder.getDistance();
	}

	@Override
	public void initDefaultCommand() {
	}
}
