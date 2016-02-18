package com.spikes2212.robot2016.subsystems;

import com.spikes2212.robot2016.Constants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Triz extends Subsystem {

	private Talon motor;
	private DigitalInput upLimit, downLimit;
	private Encoder encoder;
	private double phase;
	
	private DigitalInput bumpLimit;

	public Triz(Talon motor, DigitalInput up, DigitalInput down, DigitalInput bumpLimit, Encoder encoder) {
		this.motor = motor;
		this.upLimit = up;
		this.downLimit = down;
		this.bumpLimit=bumpLimit;
		this.encoder = encoder;
		this.encoder.setDistancePerPulse(Constants.TRIZ_ANGLE_PER_PULSE);
		this.phase = Constants.TRIZ_UP_ANGLE;
	}

	public Triz(int trizTalonPort, int upPort, int downPort,int bumpPort, int underPortcullisChannel, int encoderChannelA,
			int encoderChannelB) {
		this(new Talon(trizTalonPort), new DigitalInput(upPort), new DigitalInput(downPort),new DigitalInput(bumpPort),
				new Encoder(encoderChannelA, encoderChannelB));
	}

	public boolean canMove(double speed) {
		return !(speed > 0 && isUp() || speed < 0 && isDown());
	}

	public void tryMove(double speed) {
		if (canMove(speed)) {
			motor.set(speed);
		}
	}

	public void stop() {
		motor.set(0);
	}

	public boolean isUp() {
		return !upLimit.get();
	}

	public boolean isDown() {
		return !downLimit.get();
	}
	public boolean isBump() {
		return !bumpLimit.get();
	}
	

	public void calibrate() {
		if (isUp()) {
			phase = Constants.TRIZ_UP_ANGLE;
		} else if (isDown()) {
			phase = Constants.TRIZ_DOWN_POSITION;
		} else {
			phase = getAngle();
		}
		encoder.reset();
	}

	public double getAngle() {
		return phase + encoder.getDistance();
	}

	public void initDefaultCommand() {
	}

}
