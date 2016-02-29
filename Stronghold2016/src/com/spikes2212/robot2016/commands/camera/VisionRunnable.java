package com.spikes2212.robot2016.commands.camera;

import static com.spikes2212.robot2016.Robot.vision;

import edu.wpi.first.wpilibj.Timer;

public class VisionRunnable implements Runnable {

	@Override
	public void run() {
		vision.stream();
		Timer.delay(0.005);
	}

}
