package com.spikes2212.robot2016.util;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

public class Gearbox {
	private VictorSP front, rear;
	private Encoder frontEncoder, rearEncoder;

	public Gearbox(VictorSP front, VictorSP rear, Encoder frontEncoder, Encoder rearEncoder) {
		this.front = front;
		this.rear = rear;
		this.frontEncoder = frontEncoder;
		this.rearEncoder = rearEncoder;
	}

	public Gearbox(int frontPort, int rearPort, int frontEncoderChannelA, int frontEncoderChannelB,
			int rearEncoderChannelA, int rearEncoderChannelB) {
		front = new VictorSP(frontPort);
		rear = new VictorSP(rearPort);
		frontEncoder = new Encoder(frontEncoderChannelA, frontEncoderChannelB);
		rearEncoder = new Encoder(rearEncoderChannelA, rearEncoderChannelB);
	}

	public void set(double speed) {
		front.set(speed);
		rear.set(speed);
	}
}
