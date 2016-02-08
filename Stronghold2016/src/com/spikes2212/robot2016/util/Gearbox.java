package com.spikes2212.robot2016.util;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Gearbox {
	private SpeedController front, rear;
	private Encoder encoder;

	public Gearbox(SpeedController front, SpeedController rear, Encoder encoder) {
		this.front = front;
		this.rear = rear;
		this.encoder = encoder;
	}

	public Gearbox(int frontPort, int rearPort, int encoderChannelA, int encoderChannelB) {
		this(new Talon(frontPort), new Talon(rearPort), new Encoder(encoderChannelA, encoderChannelB));
	}

	public void set(double speed) {
		front.set(speed);
		rear.set(speed);
	}

	public void setDistancePerPulse(double distancePerPulse) {
		encoder.setDistancePerPulse(distancePerPulse);
	}

	public double getDistance() {
		return encoder.getDistance();
	}

	public double getRate() {
		return encoder.getRate();
	}

	public void reset() {
		encoder.reset();
	}
}
