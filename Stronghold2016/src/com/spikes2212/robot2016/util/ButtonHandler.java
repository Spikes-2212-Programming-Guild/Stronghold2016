package com.spikes2212.robot2016.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ButtonHandler implements Runnable {
	private boolean[] lastIteration = new boolean[4];

	public ButtonHandler() {
		for (int i = 0; i < lastIteration.length; i++) {
			SmartDashboard.putBoolean("DB/Button " + i, false);
			lastIteration[i] = false;
		}
	}

	@Override
	public void run() {
		try {
			boolean[] newIteration = new boolean[4];
			for (int i = 0; i < newIteration.length; i++) {
				newIteration[i] = SmartDashboard.getBoolean("DB/Button " + i, false);
				if (newIteration[i] && !lastIteration[i]) {
					for (int j = 0; j < lastIteration.length; j++) {
						if (i != j) {
							SmartDashboard.putBoolean("DB/Button " + j, false);
						}
					}
				}
			}
			lastIteration = newIteration;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getPressed() {
		for (int i = 0; i < lastIteration.length; i++) {
			if (lastIteration[i]) {
				return i;
			}
		}
		return -1;
	}

}
