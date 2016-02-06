package com.spikes2212.robot2016.subsystems;

import com.spikes2212.robot2016.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Triz extends Subsystem {

	private CANTalon motor;
	private DigitalInput upLimit, downLimit;
	private Encoder encoder;
	private double phase;

	public Triz(CANTalon motor, DigitalInput up, DigitalInput down,
			Encoder encoder) {
		this.motor = motor;
		this.upLimit = up;
		this.downLimit = down;
		this.encoder = encoder;
		this.encoder.setDistancePerPulse(Constants.TRIZ_DISTANCE_PER_PULSE);
		this.phase = 0;
	}

	public Triz(int trizTalonPort, int upPort, int downPort,
			int underPortcullisChannel, int encoderChannelA, int encoderChannelB) {
		this(new CANTalon(trizTalonPort), new DigitalInput(upPort),
				new DigitalInput(downPort), new Encoder(encoderChannelA,
						encoderChannelB));
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

	public void calibrate() {
		if (isUp()) {
			phase = Constants.LIFTER_UP_DISTANCE;
		} else if (isDown()) {
			phase = Constants.LIFTER_DOWN_DISTANCE;
		} else {
			phase = getPosition();
		}
		encoder.reset();
	}

	public double getPosition() {
		return phase + encoder.getDistance();
	}

	@Override
	public void initDefaultCommand() {
	}

}
