package com.spikes2212.robot2016.util;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

public class Gearbox {
	private VictorSP front, rear;
	private Encoder encoder;

	public Gearbox(VictorSP front, VictorSP rear, Encoder encoder) {
		this.front = front;
		this.rear = rear;
		this.encoder = encoder;
	}

	public Gearbox(int frontPort, int rearPort, int encoderChannelA, int encoderChannelB) {
		this(new VictorSP(frontPort), new VictorSP(rearPort), new Encoder(encoderChannelA, encoderChannelB));
	}

	public void set(double speed) {
		front.set(speed);
		rear.set(speed);
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
