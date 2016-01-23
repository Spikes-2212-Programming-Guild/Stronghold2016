package com.spikes2212.robot2016.util;

import edu.wpi.first.wpilibj.Talon;

public class Gearbox {
	private Talon rear, front;

	public Gearbox(Talon rear, Talon right) {
		this.rear = rear;
		this.front = right;
	}

	public Gearbox(int rearPort, int frontPort) {
		super();
		front = new Talon(frontPort);
		rear = new Talon(rearPort);
	}

	public void set(double speed) {
		front.set(speed);
		rear.set(speed);
	}

	public double getSpeed() {
		return front.getSpeed();
	}
}
