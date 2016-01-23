package com.spikes2212.robot2016.util;

import edu.wpi.first.wpilibj.VictorSP;

public class Gearbox {
	private VictorSP front, rear;

	public Gearbox(VictorSP front, VictorSP rear) {
		this.front = front;
		this.rear = rear;
	}

	public Gearbox(int frontPort, int rearPort) {
		front = new VictorSP(frontPort);
		rear = new VictorSP(rearPort);
	}

	public void set(double speed) {
		front.set(speed);
		rear.set(speed);
	}
}
